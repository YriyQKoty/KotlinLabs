package com.example.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView

class ToDoAdapter(context: Context, toDoList: MutableList<ToDoModel>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoList
    private var updateAndDelete: UpdateAndDelete = context as UpdateAndDelete

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val Uid: String = itemList.get(position).Uid as String
        val itemTextTextData = itemList.get(position).itemDataText as String
        val done = itemList.get(position).done as Boolean

        val view: View
        val viewHolder : ListViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.row_itemlayout,parent, false)
            viewHolder = ListViewHolder(view)
            view.tag = viewHolder
        }
        else {
            view = convertView
            viewHolder = view.tag as ListViewHolder
        }


        viewHolder.textLabel.text = itemTextTextData
        viewHolder.isDone.isChecked = done

        viewHolder.isDone.setOnClickListener {
            updateAndDelete.modifyItem(Uid, !done)
        }

        viewHolder.isDeleted.setOnClickListener {
            updateAndDelete.onItemDelete(Uid)
        }

        return view
    }

    private class ListViewHolder (row: View?) {
        val textLabel: TextView = row!!.findViewById(R.id.item_textView) as TextView
        val isDone: CheckBox = row!!.findViewById(R.id.checkbox) as CheckBox
        val isDeleted: ImageButton = row!!.findViewById(R.id.close) as ImageButton
    }

    override fun getItem(position: Int): Any {
        return itemList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

}