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

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "EREBUS_data"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        check_if_password_isset()
    }

    fun login(view: View){
        val passinp = findViewById<EditText>(R.id.editTextPassword).text.toString()
        val hashed_pass = hash_pass(passinp)
        val hash = get_hash()
        if (hash == "default"){
            val intent = Intent(this, set_password::class.java)
            startActivity(intent)
        }else if (hash == hashed_pass){
            Toast.makeText(applicationContext, "logged in!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Wrong password!", Toast.LENGTH_SHORT).show()
        }
    }

    fun check_if_password_isset(){
        val hash = get_hash()
        if(hash == "default"){
            val intent = Intent(this, set_password::class.java)
            startActivity(intent)
        }
    }

    fun get_hash(): String?{
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val hash = sharedPreferences.getString("hash", "default")
        return hash
    }

    fun hash_pass(input: String): String{
        val bytes = MessageDigest.getInstance("SHA-1").digest(input.toByteArray())
        val hex_hash = bytes.toHex()
        return hex_hash
    }

    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}