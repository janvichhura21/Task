package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.task.databinding.ActivityLoginScreenBinding
import com.example.task.utils.Utils
import com.example.task.utils.setRequired
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding
    lateinit var firebaseAuth: FirebaseAuth
    private val requiredFields: List<TextInputLayout> by lazy {
        listOf(
            binding.layoutEmail,
            binding.layoutPassword
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title="Screen 1"
        firebaseAuth= FirebaseAuth.getInstance()
        binding.btn.setOnClickListener {
            if (!Utils.isAnyFieldEmpty(requiredFields)) {
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()
              //  if (password >= 6.toString()) {
                    setuplogin(email, password)
              //  }
            }
        }
        requiredFields.forEach {
            it.setRequired()
        }

    }

    private fun setuplogin(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "unSuccessfully Login", Toast.LENGTH_SHORT).show()
            }
    }
}