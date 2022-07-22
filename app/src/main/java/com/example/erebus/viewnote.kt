package com.example.erebus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

var filename: String = ""

class viewnote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewnote)
        filename = intent.getStringExtra(NOTE_NAME).toString()
        val note_content = readfile(filename)
        val noteView = findViewById<EditText>(R.id.editTextTextMultiLine2)
        noteView.setText(note_content)
    }

    fun readfile(filename: String): String{
        val text = applicationContext.openFileInput(filename).bufferedReader().useLines { lines ->
            lines.fold(""){ some, text ->
                "$some\n$text"
            }
        }.toString()
        return text
    }

    fun save(view: View){
        val plaintext = findViewById<EditText>(R.id.editTextTextMultiLine2).text.toString()
        val title = filename
        if(title != ""){
            try {
                applicationContext.openFileOutput(title, Context.MODE_PRIVATE).use{
                    it.write(plaintext.toByteArray())
                }
            }catch (e: Exception){
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Please enter title", Toast.LENGTH_SHORT).show()
        }
    }

    fun delete(view: View){

    }
}