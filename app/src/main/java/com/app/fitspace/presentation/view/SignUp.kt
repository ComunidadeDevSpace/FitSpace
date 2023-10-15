package com.app.fitspace.presentation.view

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.cardview.widget.CardView
import com.app.fitspace.R
import com.app.fitspace.data.local.AppDataBase
import com.app.fitspace.data.local.UserDao
import com.app.fitspace.data.model.User
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignUp : AppCompatActivity() {

    private var selectedDate: String? = null
    private lateinit var dateText: TextView
    private lateinit var rbFemale: RadioButton
    private lateinit var rbMale: RadioButton
    private lateinit var userDao: UserDao
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val passwordWarning = findViewById<TextView>(R.id.textView_warning)
        val btnSave = findViewById<Button>(R.id.btn_save)

        userDao = AppDataBase.getInstance(this).userDao()

        val userId = intent.getLongExtra("userId", 0)

        currentUser = userDao.getUserById(userId)

        val nameEditText = findViewById<EditText>(R.id.name_edt_text)
        val emailEditText = findViewById<EditText>(R.id.email_edt_text)
        val nicknameEditText = findViewById<EditText>(R.id.nick_edt_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edt_text)
        val phoneEditText = findViewById<EditText>(R.id.phone_edt_text)
        val birthTextView = findViewById<TextView>(R.id.textview_date)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.radiogroup_gender)

        currentUser?.let {
            nameEditText.setText(it.name)
            emailEditText.setText(it.email)
            nicknameEditText.setText(it.nickname)
            passwordEditText.setText(it.password)
            phoneEditText.setText(it.phone)
            birthTextView.text = it.birth
        }

        btnSave.setOnClickListener {
            if(isPasswordValid(passwordEditText.text.toString())){

                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val nickname = nicknameEditText.text.toString()
                val password = passwordEditText.text.toString()
                val phone = phoneEditText.text.toString()
                val birth = birthTextView.text.toString()
                val gender = genderRadioGroup.isSelected.toString()

                if (currentUser == null) {
                    // Se currentUser for nulo, insira um novo usuário
                    val newUser = User(name = name, email = email, nickname = nickname, password = password, phone = phone, birth = birth, gender = gender)
                    val userId = userDao.insertUser(newUser)
                    currentUser = userDao.getUserById(userId)
                } else {
                    // Se currentUser não for nulo, o usuário existente é atualizado
                    currentUser?.apply {
                        this.name = name
                        this.email = email
                        this.nickname = nickname
                        this.password = password
                        this.phone = phone
                        this.birth = birth
                        this.gender = gender
                    }
                    userDao.updateUser(currentUser!!)
                }

                finish()

            } else if (!isPasswordValid(passwordEditText.text.toString())){
                passwordWarning.visibility = View.VISIBLE
                Snackbar.make(btnSave, "Senha inválida", Snackbar.LENGTH_LONG).show()
            }
        }

        val imageView = findViewById<ImageView>(R.id.back_toolbar)
        imageView.setOnClickListener {
            showAlertDialog()
        }

        val dataButton = findViewById<CardView>(R.id.date_cardview)

        val calendarBox = Calendar.getInstance()
        val dateBox = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            calendarBox.set(Calendar.YEAR, year)
            calendarBox.set(Calendar.MONTH, month)
            calendarBox.set(Calendar.DAY_OF_MONTH, day)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
            dateFormat.format(calendarBox.time)
            selectedDate = dateFormat.format(calendarBox.time)
            dateText = findViewById(R.id.date_edt_text)
            dateText.text = selectedDate

//            R.style.TimePickerDialogTheme
        }

        dataButton.setOnClickListener {
            DatePickerDialog(
                this,
                dateBox,
                calendarBox.get(Calendar.YEAR),
                calendarBox.get(Calendar.MONTH),
                calendarBox.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        rbFemale = findViewById(R.id.rb_female)
        rbMale = findViewById(R.id.rb_male)
    }

//    private fun updateText(calendar: Calendar) {
//        val dateFormat = "dd/MM/yyyy"
//        val simple = SimpleDateFormat(dateFormat, Locale.UK)
//        simple.format(calendar.time)
//
//    }

    fun onRadioButtonClicked(view: View) {
        val isSelected = (view as AppCompatRadioButton).isChecked
        when (view.id) {
            R.id.rb_female -> {
                if (isSelected) {
                    rbMale.setTextColor(Color.GRAY)
                    rbMale.isChecked = false
                }
            }

            R.id.rb_male -> {
                if (isSelected) {
                    rbFemale.setTextColor(Color.GRAY)
                    rbFemale.isChecked = false
                }
            }
        }
    }

    //Mostra uma mensagem ao clicar no botão de salvar.
    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Tem certeza que deseja sair?")
            .setMessage("Seus dados não serão salvos.")
            .setPositiveButton("Sim") { dialog, which ->
                finish()
            }
            .setNegativeButton("Não") { dialog, which -> }
            .create()
        alertDialog.show()
    }

    private fun isPasswordValid(password: String): Boolean{
        val hasUpperCase = password.any{
            it.isUpperCase()
        }

        val hasLowerCase = password.any(){
            it.isLowerCase()
        }

        val hasDigit = password.any{
            it.isDigit()
        }

        val isLenghtValid = password.length >= 8

        return hasUpperCase && hasLowerCase && hasDigit && isLenghtValid
    }
}