package com.app.fitspace.presentation.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRadioButton
import com.app.fitspace.R

class GoalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        val userName = findViewById<TextView>(R.id.userName)
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
        val saveButton = findViewById<Button>(R.id.save_btn)


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
