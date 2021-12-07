package com.example.helloworld

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.helloworld.PaintView.Companion.colorList
import com.example.helloworld.PaintView.Companion.currentBrush
import com.example.helloworld.PaintView.Companion.pathList


class MainActivity : AppCompatActivity(){

    companion object {
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setListenersForBtns()
    }

    private fun setListenersForBtns() {
        val redBtn = findViewById<ImageButton>(R.id.redColor)
        val greenBtn = findViewById<ImageButton>(R.id.greenColor)
        val blueBtn = findViewById<ImageButton>(R.id.blueColor)
        val eraserBtn = findViewById<ImageButton>(R.id.eraser)

        redBtn.setOnClickListener {
            Toast.makeText(this, "Red licked", Toast.LENGTH_LONG).show()
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }

        greenBtn.setOnClickListener {
            Toast.makeText(this, " Green Clicked", Toast.LENGTH_LONG).show()
            paintBrush.color = Color.GREEN
            currentColor(paintBrush.color)
        }

        blueBtn.setOnClickListener {
            Toast.makeText(this, "Blue Clicked", Toast.LENGTH_LONG).show()
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }

        eraserBtn.setOnClickListener {
            Toast.makeText(this, "Eraser Clicked", Toast.LENGTH_LONG).show()
            pathList.clear()
            colorList.clear()
            path.reset()

        }


    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }

}