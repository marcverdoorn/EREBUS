package com.example.erebus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

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
        var files = applicationContext.fileList()
        var stringbuilder = ""
        for(i in files){
            stringbuilder += i.toString() + " "
        }
        Toast.makeText(applicationContext, stringbuilder, Toast.LENGTH_SHORT).show()
    }

}