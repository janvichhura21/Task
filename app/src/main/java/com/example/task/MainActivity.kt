package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task.databinding.ActivityMainBinding
import com.example.task.model.User
import com.example.task.utils.Utils
import com.example.task.utils.setRequired
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val requiredFields: List<TextInputLayout> by lazy {
        listOf(
            binding.layoutName,
            binding.layoutAge,
            binding.layoutGender,
            binding.layoutEmailId,
            binding.layoutPhone
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Screen 2"
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        onClickBtn()
        val list= listOf(true,false)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, list)
        binding.edtGender.setAdapter(arrayAdapter)
        onLevelClick()

    }



    private fun onLevelClick() {
        val LevelMap:HashMap<Boolean,String> = hashMapOf(true to "Male" ,false to "Female")
        val level=LevelMap.values.toList()
        val arrayAdapter  = ArrayAdapter(this, R.layout.dropdown_item, level)
        binding.edtGender.setAdapter(arrayAdapter)

    }

    private fun getLevel(spicyLevel: String): Boolean {
        when(spicyLevel){
            "Male"->return true
            "Female"->return false

        }
        return true
    }
    private fun onClickBtn() {
        binding.saveBtn.setOnClickListener {

            if (!Utils.isAnyFieldEmpty(requiredFields)) {
                val user = User(
                    name = binding.edtName.text.toString(),
                    age = binding.edtAge.text.toString().toInt(),
                    gender = getLevel(binding.edtGender.text.toString()),
                    email = binding.edtEmailId.text.toString(),
                    phoneNumber = binding.edtPhone.text.toString()
                )
                viewModel.addUsers(user)
                val intent = Intent(this, Details::class.java)
                intent.putExtra("name", user.name)
                intent.putExtra("age", user.age)
                intent.putExtra("Email", user.email)
                intent.putExtra("phoneNo", user.phoneNumber)
                startActivity(intent)
                Toast.makeText(this, "$user", Toast.LENGTH_SHORT).show()
            }
        }
        requiredFields.forEach {
            it.setRequired()
        }
    }
}