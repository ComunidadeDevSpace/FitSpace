package com.app.fitspace.presentation.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatRadioButton
import com.app.fitspace.R
import com.app.fitspace.data.model.UserGoals
import com.app.fitspace.presentation.viewmodel.UserGoalsViewModel
import com.app.fitspace.utils.ActionTypeGoals
import com.app.fitspace.utils.GoalsAction

class GoalsActivity : AppCompatActivity() {


    private val viewModel : UserGoalsViewModel by viewModels {
        UserGoalsViewModel.getVmFactory(application)
    }

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
        val saveButton = findViewById<Button>(R.id.save_btn_goals)



        saveButton.setOnClickListener {
            val weight = editTextWeight.text
            val height = editTextHeight.text

            addOrUpdate(weight.toString().toDouble(),height.toString().toDouble(), ActionTypeGoals.INSERT,0)
        }


    }

    fun addOrUpdate(
        weight:Double,
        height: Double,
        actionTypeGoals: ActionTypeGoals,
        id:Int){
        val userGoals = UserGoals(id,weight,height)
        performAction(userGoals,actionTypeGoals)
    }

    private fun performAction(userGoals: UserGoals, actionType: ActionTypeGoals){
        val goalsAction = GoalsAction(userGoals, actionType.name)
        viewModel.execute(goalsAction)
        finish()
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