package com.example.erebus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.security.MessageDigest

class set_password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_password)
    }

    fun setPassword(view: View){
        val pinp1 = findViewById<EditText>(R.id.passwordinput1)
        val pinp2 = findViewById<EditText>(R.id.passwordinput2)
        val pass1 = pinp1.text.toString()
        val pass2 = pinp2.text.toString()

        if (pass1 == pass2 && pass1 != "" && pass2 != ""){
            hash_pass(pass1)
        }else{
            Toast.makeText(applicationContext, "Re-confim password", Toast.LENGTH_SHORT).show()
        }
    }

    fun hash_pass(input: String){
        val bytes = MessageDigest.getInstance("SHA-1").digest(input.toByteArray())
        val hex_hash = bytes.toHex()
        Toast.makeText(applicationContext, hex_hash, Toast.LENGTH_SHORT).show()
    }

    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}