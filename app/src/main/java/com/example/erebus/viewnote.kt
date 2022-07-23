package com.example.erebus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

var filename: String = ""

class viewnote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewnote)
        filename = intent.getStringExtra(NOTE_NAME).toString()
        val note_content = readfile(filename)
        val noteView = findViewById<EditText>(R.id.editTextTextMultiLine2)
        noteView.setText(note_content)
        val title_view = findViewById<TextView>(R.id.title_view)
        title_view.text = filename
    }

    fun readfile(filename: String): String{
        try {
            val text = applicationContext.openFileInput(filename).bufferedReader().useLines { lines ->
                lines.fold(""){ some, text ->
                    "$some\n$text"
                }
            }.toString()
            return text
        }catch (e: Exception){
            return e.toString()
        }
    }

    fun save(view: View){
        val plaintext = findViewById<EditText>(R.id.editTextTextMultiLine2).text.toString()
        val title = filename
        if(title != ""){
            try {
                applicationContext.openFileOutput(title, Context.MODE_PRIVATE).use{
                    it.write(plaintext.toByteArray())
                }
                intent = Intent(this, menu::class.java)
                startActivity(intent)
            }catch (e: Exception){
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Please enter title", Toast.LENGTH_SHORT).show()
        }
    }

    fun delete(view: View){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete note")
        builder.setMessage("Are you sure you want to delete this note?")
        builder.setPositiveButton("YES"){
            dialogInterface, which ->
            try {
                applicationContext.deleteFile(filename)
                intent = Intent(this, menu::class.java)
                startActivity(intent)
            }catch (e: Exception){
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("NO"){
            dialogInterface, which ->
            Toast.makeText(applicationContext, "Didn't delete note", Toast.LENGTH_SHORT).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}