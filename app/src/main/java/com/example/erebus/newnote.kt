package com.example.erebus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import javax.crypto.KeyGenerator

class newnote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newnote)
        encrypt()
        //readfile("test")
    }

    fun encrypt(){
        val keygen = KeyGenerator.getInstance("AES")
        keygen.init(256)
        val key = keygen.generateKey()
        val tekst = key.encoded.toString()
        val textView = findViewById<TextView>(R.id.textView2)
        textView.text = tekst
    }

    fun save(view: View){
        val plaintext = findViewById<EditText>(R.id.editTextTextMultiLine).text.toString()
        val title = findViewById<EditText>(R.id.editTextTitle).text.toString()
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

    fun readfile(filename: String){
        val text = applicationContext.openFileInput(filename).bufferedReader().useLines { lines ->
            lines.fold(""){ some, text ->
                "$some\n$text"
            }
        }.toString()
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    fun delete(view: View){
        finish()
        overridePendingTransition(0, 0)
        startActivity(getIntent())
        overridePendingTransition(0, 0)
    }
}