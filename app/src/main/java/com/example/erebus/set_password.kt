package com.example.erebus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.security.MessageDigest

class set_password : AppCompatActivity() {
    private val sharedPrefFile = "EREBUS_data"

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
            var hash = hash_pass(pass1)
            save_hash(hash)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext, "Re-confim password", Toast.LENGTH_SHORT).show()
        }
    }

    fun hash_pass(input: String): String{
        val bytes = MessageDigest.getInstance("SHA-1").digest(input.toByteArray())
        val hex_hash = bytes.toHex()
        Toast.makeText(applicationContext, hex_hash, Toast.LENGTH_SHORT).show()
        return hex_hash
    }

    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }

    fun save_hash(input: String){
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("hash", input)
        editor.apply()
        editor.commit()
    }
}