package com.example.mscapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ProfileSetupActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null

    // Launcher to pick image from gallery
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                selectedImageUri = uri
                findViewById<ImageView>(R.id.profileImageView).setImageURI(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setup)
        supportActionBar?.hide()

        val profileImage = findViewById<ImageView>(R.id.profileImageView)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val continueButton = findViewById<Button>(R.id.continueButton)

        // Tap profile pic to open gallery
        profileImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        continueButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USER_NAME", name)
            intent.putExtra("PROFILE_IMAGE_URI", selectedImageUri?.toString())
            startActivity(intent)
            finish()
        }
    }
}