package com.example.erebus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import androidx.security.crypto.MasterKeys.getOrCreate
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import javax.crypto.Cipher
import javax.crypto.KeyGenerator

class newnote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newnote)
        //val test = encrypt("test string").toString()
        //val textView = findViewById<TextView>(R.id.textView2)
        //textView.text = test
        //readfile("test")
    }

    fun encrypt(data: String){
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKey.getOrCreate(keyGenParameterSpec)
    }

    fun save(view: View){
        val plaintext = findViewById<EditText>(R.id.editTextTextMultiLine).text.toString()
        val title = findViewById<EditText>(R.id.editTextTitle).text.toString()
        if(title != ""){
            try {
                applicationContext.openFileOutput(title, Context.MODE_PRIVATE).use{
                    it.write(plaintext.toByteArray())
                }
                intent = Intent(this, menu::class.java)
                overridePendingTransition(0, 0)
                startActivity(intent)
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