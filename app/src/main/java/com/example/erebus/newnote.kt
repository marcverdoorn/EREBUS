package com.example.erebus

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
        Toast.makeText(applicationContext, plaintext, Toast.LENGTH_SHORT).show()
    }

    fun delete(view: View){
        finish()
        overridePendingTransition(0, 0)
        startActivity(getIntent())
        overridePendingTransition(0, 0)
    }
}