package com.app.fitspace.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.fitspace.R
import com.app.fitspace.database.DataBase
import com.app.fitspace.database.UserEntity
import com.app.fitspace.databinding.ActivityMainBinding
import com.app.fitspace.repository.UserRepository
import com.app.fitspace.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DataBase.getDataBaseInstance(application)
        val repository = UserRepository(db)
        viewModel = UserViewModel(repository)

        binding.saveBtn.setOnClickListener {
            val name = binding.nameEdt.text.toString()
            val email = binding.emailEdt.text.toString()

            val user = UserEntity(name, email)

            viewModel.insertUser(user)
        }
    }
}