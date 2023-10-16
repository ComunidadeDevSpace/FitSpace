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
import androidx.lifecycle.lifecycleScope
import com.app.fitspace.R
import com.app.fitspace.databinding.ActivitySignInBinding
import com.app.fitspace.presentation.viewmodel.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var signViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


        binding.btnSave.setOnClickListener {
            val userEmail = findViewById<EditText>(R.id.email_edt_text)
            val userPassword = findViewById<EditText>(R.id.password_edt_text)

            lifecycleScope.launch(Dispatchers.Main) {
                val isUserLogged = signViewModel.validaLoginPass(userEmail.text.toString(), userPassword.text.toString())

                if (isUserLogged) {
                    val intent = Intent(this@SignIn, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@SignIn,
                        "Verifique se Email e Senha estão preenchidos ou incorretos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}