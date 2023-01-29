package com.oguzhandurmaz.kotlininstagram.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.oguzhandurmaz.kotlininstagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        if (auth.currentUser!=null){
            val intent=Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signInClicked(view: View) {
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter e-mail and password!", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent=Intent(this, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signUpClicked(view: View) {
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter e-mail and password!", Toast.LENGTH_SHORT).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                //success
                Toast.makeText(this, "New user created successfully.", Toast.LENGTH_SHORT).show()
                val intent=Intent(this, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                //failure
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        }
    }
}