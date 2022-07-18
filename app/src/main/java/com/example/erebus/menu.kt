package com.example.erebus

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.LinearLayout
import android.view.ViewGroup

const val NOTE_NAME = "com.example.EREBUS.NOTENAME"

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //readfile("test")
        show_files()
    }

    fun open_settings(view: View){
        val intent = Intent(this, settings::class.java)
        startActivity(intent)
    }

    fun new_note(view: View){
        val intent = Intent(this, newnote::class.java)
        startActivity(intent)
    }

    fun readfile(filename: String){
        val text = applicationContext.openFileInput(filename).bufferedReader().useLines { lines ->
            lines.fold(""){ some, text ->
                "$some\n$text"
            }
        }.toString()
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    fun show_files(){
        val files = applicationContext.fileList()
        for(i in files){
            add_note_view(i)
        }
    }

    fun add_note_view(title: String){
        val note_layout = findViewById<LinearLayout>(R.id.note_layout)
        val noteview: TextView = TextView(this)
        noteview.textSize = 20f
        noteview.text = title
        noteview.setPadding(20,50,20,50)
        noteview.textAlignment = View.TEXT_ALIGNMENT_CENTER
        noteview.setBackgroundColor(Color.rgb(0,51,102))
        noteview.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, viewnote::class.java).apply {
                putExtra(NOTE_NAME, title)
            }
            startActivity(intent)
        })
        note_layout.addView(noteview)
    }

}