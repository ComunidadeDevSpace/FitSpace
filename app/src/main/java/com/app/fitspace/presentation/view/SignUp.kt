package com.app.fitspace.presentation.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.cardview.widget.CardView
import com.app.fitspace.R
import com.app.fitspace.data.local.ProfilePicture
import com.app.fitspace.presentation.viewmodel.ProfilePictureViewModel
import com.google.android.material.snackbar.Snackbar
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignUp : AppCompatActivity() {

    private var selectedDate: String? = null
    private lateinit var dateText: TextView
    private lateinit var rbGroup: RadioGroup
    private lateinit var rbFemale: RadioButton
    private lateinit var rbMale: RadioButton

    private val viewModelPicture: ProfilePictureViewModel by viewModels {
        ProfilePictureViewModel.getVmFactory(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val edtPassword = findViewById<EditText>(R.id.password_edt_text)
        val passwordWarming = findViewById<TextView>(R.id.textView_warning)
        val btnSave = findViewById<Button>(R.id.btn_save)
        val iv_profile_picture = findViewById<ImageView>(R.id.iv_profile_picture)


        iv_profile_picture.setOnClickListener {
            openImageChooser()
        }

        viewModelPicture.setProfileImage(iv_profile_picture)

        btnSave.setOnClickListener {
            if(isPasswordValid(edtPassword.text.toString())){
                finish()

            } else if (!isPasswordValid(edtPassword.text.toString())){
                passwordWarming.visibility = View.VISIBLE
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

        rbGroup = findViewById(R.id.radiogroup_gender)
        rbFemale = findViewById(R.id.rb_female)
        rbMale = findViewById(R.id.rb_male)
    }

//    private fun updateText(calendar: Calendar) {
//        val dateFormat = "dd/MM/yyyy"
//        val simple = SimpleDateFormat(dateFormat, Locale.UK)
//        simple.format(calendar.time)
//
//    }

    private fun openImageChooser() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1, 1)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val croppedUri = result.uri

                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(croppedUri))

                val width = bitmap.width
                val height = bitmap.height

                val byteArray = getByteArrayFromBitmap(bitmap)

                val profilePicture = ProfilePicture(0,profilePicture = byteArray, width = width, height = height)

                viewModelPicture.insertPicture(profilePicture)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }
    private fun getByteArrayFromBitmap(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }


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