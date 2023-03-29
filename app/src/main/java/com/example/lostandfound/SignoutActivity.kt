package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.log

class SignoutActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var logout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signout)

      text = findViewById(R.id.email)
        logout=findViewById(R.id.logoutbutton)
       logout.setOnClickListener {
            Firebase.auth.signOut()
           val intent= Intent (this,SignInActivity::class.java)
           startActivity(intent)

           Toast.makeText(this,"Logout Scuccessful",Toast.LENGTH_LONG).show()
       }
        }
    }
