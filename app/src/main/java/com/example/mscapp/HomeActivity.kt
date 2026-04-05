package com.example.mscapp

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        val name = intent.getStringExtra("USER_NAME") ?: "Member"
        val imageUriString = intent.getStringExtra("PROFILE_IMAGE_URI")

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val profileImage = findViewById<ImageView>(R.id.homeProfileImage)

        welcomeText.text = "Welcome to Microsoft Club, $name! 🎉"

        if (imageUriString != null) {
            profileImage.setImageURI(Uri.parse(imageUriString))
        }
    }
}