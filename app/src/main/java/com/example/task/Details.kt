package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task.databinding.ActivityDetailsBinding

class Details : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name=intent.getStringExtra("name")
        val Email=intent.getStringExtra("Email")
        val PhoneNo=intent.getStringExtra("phoneNo")
        binding.showName.setText(name)
        binding.showEmail.setText(Email)
        binding.showPhoneNo.setText(PhoneNo)
    }

}