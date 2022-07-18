package com.example.erebus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class viewnote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewnote)
        val filename = intent.getStringExtra(NOTE_NAME).toString()
        val note_content = readfile(filename)
        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = note_content
    }

    fun readfile(filename: String): String{
        val text = applicationContext.openFileInput(filename).bufferedReader().useLines { lines ->
            lines.fold(""){ some, text ->
                "$some\n$text"
            }
        }.toString()
        return text
    }
}