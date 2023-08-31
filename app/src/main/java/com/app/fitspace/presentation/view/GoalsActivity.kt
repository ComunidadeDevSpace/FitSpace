package com.app.fitspace.presentation.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.lifecycle.ViewModelProvider
import com.app.fitspace.R
import com.app.fitspace.data.model.UserGoals
import com.app.fitspace.presentation.viewmodel.UserGoalsViewModel

class GoalsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)



        val editTextWeight = findViewById<EditText>(R.id.edit_weight)
        val editTextHeight = findViewById<EditText>(R.id.edit_height)
        val editTextUpperBody = findViewById<EditText>(R.id.edit_upper_body)
        val editTextNeck = findViewById<EditText>(R.id.edit_neck)
        val editTextHips = findViewById<EditText>(R.id.edit_hips)
        val editTextWaist = findViewById<EditText>(R.id.edit_waist)
        val editTextRightArm = findViewById<EditText>(R.id.edit_right_arm)
        val editTextLeftArm = findViewById<EditText>(R.id.edit_left_arm)
        val editTextRightThigh = findViewById<EditText>(R.id.edit_right_thigh)
        val editTextLeftThigh = findViewById<EditText>(R.id.edit_left_thigh)
        val editTextRightCalv = findViewById<EditText>(R.id.edit_right_calv)
        val editTextLeftCalv = findViewById<EditText>(R.id.edit_left_calv)
        val radioButtonManter = findViewById<RadioButton>(R.id.rb_manter)
        val radioButtonGanhar = findViewById<RadioButton>(R.id.rb_ganhar)
        val radioButtonEmagrecer = findViewById<RadioButton>(R.id.rb_emagrecer)
        val spinner = findViewById<Spinner>(R.id.spinner_weekly_exercise)
        val saveButton = findViewById<Button>(R.id.save_btn)

        saveButton.setOnClickListener {
            val userGoals = UserGoals(
                weight = editTextWeight.text.toString().toDouble(),
                height = editTextHeight.text.toString().toDouble(),
                upperBody = editTextUpperBody.text.toString().toDouble(),
                neck = editTextNeck.text.toString().toDouble(),
                hips = editTextHips.text.toString().toDouble(),
                waist = editTextWaist.text.toString().toDouble(),
                rightArm = editTextRightArm.text.toString().toDouble(),
                leftArm = editTextLeftArm.text.toString().toDouble(),
                rightThigh = editTextRightThigh.text.toString().toDouble(),
                leftThigh = editTextLeftThigh.text.toString().toDouble(),
                rightCalv = editTextRightCalv.text.toString().toDouble(),
                leftCalv = editTextLeftCalv.text.toString().toDouble()
            )

            val weight = editTextWeight.text.toString().toDouble()
            val height = editTextHeight.text.toString().toDouble()
            /*
            val upperBody = editTextUpperBody.text.toString().toDouble()
            val neck = editTextNeck.text.toString().toDouble()
            val hips = editTextHips.text.toString().toDouble()
            val waist = editTextWaist.text.toString().toDouble()
            val rightArm = editTextRightArm.text.toString().toDouble()
            val leftArm = editTextLeftArm.text.toString().toDouble()
            val rightThigh = editTextRightThigh.text.toString().toDouble()
            val leftThigh = editTextLeftThigh.text.toString().toDouble()
            val rightCalv = editTextRightCalv.text.toString().toDouble()
            val leftCalv = editTextLeftCalv.text.toString().toDouble()
            */


        }

    }




    fun onRadioButtonClicked(view: View) {
        val radioButtonManter = findViewById<RadioButton>(R.id.rb_manter)
        val radioButtonGanhar = findViewById<RadioButton>(R.id.rb_ganhar)
        val radioButtonEmagrecer = findViewById<RadioButton>(R.id.rb_emagrecer)

        val isSelected = (view as AppCompatRadioButton).isChecked
        when (view.id) {
            R.id.rb_manter -> {
                if (isSelected) {
                    radioButtonGanhar.setTextColor(Color.GRAY)
                    radioButtonEmagrecer.setTextColor(Color.GRAY)


                }
            }

            R.id.rb_ganhar -> {
                if (isSelected) {
                    radioButtonManter.setTextColor(Color.GRAY)
                    radioButtonEmagrecer.setTextColor(Color.GRAY)
                }
            }

            R.id.rb_emagrecer -> {
                radioButtonGanhar.setTextColor(Color.GRAY)
                radioButtonManter.setTextColor(Color.GRAY)
            }
        }
    }
}