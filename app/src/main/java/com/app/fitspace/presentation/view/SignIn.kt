package com.app.fitspace.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.app.fitspace.data.model.User
import com.app.fitspace.databinding.ActivitySignInBinding
import com.app.fitspace.presentation.viewmodel.UserViewModel

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var userViewModel: UserViewModel
    //private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        userViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

        /**
         * Example how-to use userViewModel to input data
         * 1 - created a user instance with your data
         * 2 - called ViewModel with the respective method and put the user object
         */
        val user = User(
            "Jeferson Barros",
            "im.jbalves@gmail.com",
            "jeff",
            "123456",
            "+554199999-9999",
            "16/09/1985",
            "male"
        )
        userViewModel.insertUser(user)

        binding.saveBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}