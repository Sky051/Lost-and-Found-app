package com.example.lostandfound

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView

class splash : AppCompatActivity() {
   private lateinit var image :ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       Handler(Looper.getMainLooper()).postDelayed({
           val intent=Intent(this,SignInActivity::class.java)
           startActivity(intent)
           finish()
       },  3000)
    }
}