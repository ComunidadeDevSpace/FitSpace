package com.app.fitspace.presentation.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatRadioButton
import com.app.fitspace.R
import com.app.fitspace.data.model.UserGoals
import com.app.fitspace.presentation.viewmodel.ProfilePictureViewModel
import com.app.fitspace.presentation.viewmodel.UserGoalsViewModel
import com.app.fitspace.utils.ActionTypeGoals
import com.app.fitspace.utils.GoalsAction

class GoalsActivity : AppCompatActivity() {

    lateinit var spinnerSelected:String

    private val viewModel : UserGoalsViewModel by viewModels {
        UserGoalsViewModel.getVmFactory(application)
    }


    private val viewModelPicture:ProfilePictureViewModel by viewModels {
        ProfilePictureViewModel.getVmFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        //Ajeita a toolbar
        val tvHello = findViewById<TextView>(R.id.tv_toolbar_main_hello)
        tvHello.visibility = View.GONE

        val tvNameToolbar = findViewById<TextView>(R.id.tv_toolbar_name)
        val scroll = findViewById<ScrollView>(R.id.scrollView2)

        val marginInDp = 36
        val marginInPixels = (marginInDp * resources.displayMetrics.density).toInt()
        val layoutParams = tvNameToolbar.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.marginStart = marginInPixels
        tvNameToolbar.layoutParams = layoutParams

        if (isDarkTheme()){
            scroll.setBackgroundColor(Color.parseColor("#000000"))
        }else {
            scroll.setBackgroundColor(Color.parseColor("#3BB6FB"))
        }

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



        val profilePicture = findViewById<ImageView>(R.id.iv_toolbar_main_profile)

        viewModelPicture.setProfileImage(profilePicture)


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSpinner = parent?.getItemAtPosition(position)
                spinnerSelected = selectedSpinner.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                /*
                val warning = findViewById<TextView>(R.id.emptyFieldWeeklyExercise)
                warning.visibility = View.VISIBLE
                Snackbar.make(saveButton, "Preencha os campos obrigatórios", Snackbar.LENGTH_LONG)
                    .show()

                 */
            }

        }



        saveButton.setOnClickListener {
            val weight = editTextWeight.text
            val height = editTextHeight.text
            val upperBody = editTextUpperBody.text
            val neck = editTextNeck.text
            val hips = editTextHips.text
            val waist = editTextWaist.text
            val rightArm = editTextRightArm.text
            val leftArm = editTextLeftArm.text
            val rightThigh = editTextRightThigh.text
            val leftThigh = editTextLeftThigh.text
            val rightCalv = editTextRightCalv.text
            val leftCalv = editTextLeftCalv.text
            val goal = if (radioButtonEmagrecer.isSelected) "Emagrecer" else if (radioButtonManter.isSelected) "Manter" else "Ganhar"
            val spinner = spinnerSelected

            addOrUpdate(weight.toString().toDouble(),height.toString().toDouble(), upperBody.toString().toDouble(),
                neck.toString().toDouble(),hips.toString().toDouble(),waist.toString().toDouble(),rightArm.toString().toDouble(),
                leftArm.toString().toDouble(),rightThigh.toString().toDouble(),leftThigh.toString().toDouble(), rightCalv.toString().toDouble(),
                leftCalv.toString().toDouble(),goal,spinner,ActionTypeGoals.INSERT,0)
        }


    }


    private fun isDarkTheme(): Boolean {
        val nightModeFlags = resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES
    }

    private fun addOrUpdate(
        weight:Double,
        height: Double,
        upperBody:Double,
        neck:Double,
        hips:Double,
        waist:Double,
        rightArm:Double,
        leftArm:Double,
        rightThigh:Double,
        leftThigh:Double,
        rightCalv:Double,
        leftCalv:Double,
        goal:String,
        weeklyExercise:String,
        actionTypeGoals: ActionTypeGoals,
        id:Int){
        val userGoals = UserGoals(id,weight,height,upperBody,neck,hips, waist, rightArm, leftArm, rightThigh, leftThigh, rightCalv, leftCalv,goal,weeklyExercise)
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