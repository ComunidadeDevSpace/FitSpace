package com.app.fitspace.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.app.fitspace.data.model.User
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.app.fitspace.R
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

        // Tornando o texto "Registra-se aqui." clicável direcionando à SignUp.
        val textViewLink = findViewById<TextView>(R.id.textview_link)

        val spannableString = SpannableString(getString(R.string.textNewUser))
        val startIndex = spannableString.indexOf("Registra-se aqui.")
        val endIndex = startIndex + "Registra-se aqui.".length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(widget.context, SignUp::class.java)
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textViewLink.text = spannableString
        textViewLink.movementMethod = LinkMovementMethod.getInstance()


        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        userViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

        val user = User(
            0,
            "Jeferson Barros",
            "im.jbalves@gmail.com",
            "jeff",
            "123456",
            "+554199999-9999",
            "16/09/1985",
            "male"
        )
        userViewModel.insertUser(user)

        binding.btnSave.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}