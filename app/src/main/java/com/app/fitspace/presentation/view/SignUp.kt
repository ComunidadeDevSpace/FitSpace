package com.app.fitspace.presentation.view

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.fitspace.R
import com.app.fitspace.data.model.user.User
import com.app.fitspace.databinding.ActivitySignUpBinding
import com.app.fitspace.presentation.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: UserViewModel
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this

        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        signUpViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

        binding.btnSave.setOnClickListener {
            if(isPasswordValid(binding.passwordEdtText.text.toString())){
                binding.user = User(
                    0,
                    binding.nameEdtText.text.toString(),
                    binding.emailEdtText.text.toString(),
                    binding.nickEdtText.text.toString(),
                    binding.passwordEdtText.text.toString(),
                    binding.phoneEdtText.text.toString(),
                    binding.dateEdtText.text.toString(),
                    if (binding.rbFemale.isChecked) "Female" else "Male"
                )
                saveUserData()
                finish()
            } else if (!isPasswordValid(binding.passwordEdtText.text.toString())){
                binding.textViewWarning.visibility = View.VISIBLE
                Snackbar.make(binding.btnSave, "Senha inválida", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.backToolbar.setOnClickListener {
            showAlertDialog()
        }

        binding.dateCardview.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(this,{DatePicker, year: Int, monthOfYear: Int, dayOfMont: Int ->
            val selectedDate = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())

            selectedDate.set(year,monthOfYear,dayOfMont)
            binding.dateEdtText.setText(dateFormat.format(selectedDate.time))
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

    fun onRadioButtonClicked(view: View) {
        val radioButton = view as AppCompatRadioButton

        when (radioButton.id) {
            R.id.rb_female -> {
                if (radioButton.isChecked) {
                    binding.rbMale.setTextColor(Color.GRAY)
                    binding.rbMale.isChecked = false
                }
            }

            R.id.rb_male -> {
                if (radioButton.isChecked) {
                    binding.rbFemale.setTextColor(Color.GRAY)
                    binding.rbFemale.isChecked = false
                }
            }
        }
    }

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
        return password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$"))
    }

    private fun saveUserData() {
        val user = binding.user ?: User(0, "", "", "", "", "", "", "")
        signUpViewModel.insertUser(user)
    }
}