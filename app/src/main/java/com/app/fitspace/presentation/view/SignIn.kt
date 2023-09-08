package com.app.fitspace.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.app.fitspace.R
import com.app.fitspace.databinding.ActivitySignInBinding
import com.app.fitspace.presentation.viewmodel.SignInViewModel


class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var signViewModel: SignInViewModel


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
        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textViewLink.text = spannableString
        textViewLink.movementMethod = LinkMovementMethod.getInstance()





        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        signViewModel = ViewModelProvider(this, factory).get(SignInViewModel::class.java)


        val userEmail = findViewById<EditText>(R.id.email_edt_text)
        val userPassword = findViewById<EditText>(R.id.password_edt_text)


        binding.btnSave.setOnClickListener {


            val isUserLoged = signViewModel.validaLoginPass(userEmail,userPassword)


            if (isUserLoged) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Verifique se Email e Senha estão preenchidos ou corretos", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }
}