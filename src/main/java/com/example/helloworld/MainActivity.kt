package com.example.helloworld

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Xml
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), UpdateAndDelete {

    lateinit var database:DatabaseReference
    var toDoList: MutableList<ToDoModel>? = null
    lateinit var adapter: ToDoAdapter

    private var listViewItem: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton

        listViewItem = findViewById(R.id.item_listView) as ListView


        database = FirebaseDatabase.getInstance().reference

        fab.setOnClickListener {view ->
            val alertDialog = AlertDialog.Builder(this)
            val textEditText = EditText(this)
            alertDialog.setMessage("Add TODO Item")
            alertDialog.setTitle("Enter ToDo item")
            alertDialog.setView(textEditText)
            alertDialog.setPositiveButton("Add") {dialog, i ->
                val  toDoItemData = ToDoModel.createList()
                toDoItemData.itemDataText = textEditText.text.toString()
                toDoItemData.done = false

                val newItemData = database.child("todo").push()
                toDoItemData.Uid = newItemData.key

                newItemData.setValue(toDoItemData)

                dialog.dismiss()
                Toast.makeText(this, "item saved", Toast.LENGTH_LONG).show()
            }

            alertDialog.show()
        }

        toDoList = mutableListOf<ToDoModel>()
        adapter = ToDoAdapter(this, toDoList!!)
        listViewItem!!.adapter = adapter

        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "No item added", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                toDoList!!.clear()
                addItemToList(snapshot)
            }
        })

    }

    private fun addItemToList(snapshot: DataSnapshot) {
        val items = snapshot.children.iterator()

        if (items.hasNext()) {
            val toDoIndexedValue = items.next()
            val itemsIterator = toDoIndexedValue.children.iterator()

            while (itemsIterator.hasNext()) {
                val currentItem = itemsIterator.next()
                val toDoItemData = ToDoModel.createList()

                val map = currentItem.getValue() as HashMap<String, Any>

                toDoItemData.Uid = currentItem.key
                toDoItemData.done = map.get("done") as Boolean?
                toDoItemData.itemDataText = map.get("itemDataText") as String?
                toDoList!!.add(toDoItemData)
            }
        }

        adapter.notifyDataSetChanged()
    }

    override fun modifyItem(itemUid: String, isDone: Boolean) {
        val itemReference = database.child("todo").child(itemUid)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemUid: String) {
        val itemReference = database.child("todo").child(itemUid)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }


}