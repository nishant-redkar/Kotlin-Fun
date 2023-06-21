package com.example.newhomeapp.GoogleLogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newhomeapp.R
import com.google.firebase.auth.FirebaseAuth

class Logout : AppCompatActivity() {

    private lateinit var logout: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        mAuth = FirebaseAuth.getInstance()
        logout = findViewById<Button>(R.id.logoutbtn)
        logout.setOnClickListener {
            mAuth.signOut()
            val i = Intent(this@Logout, MainActivity2::class.java)
            startActivity(i)
            finish()
        }
    }
}