package com.app.fitspace.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.app.fitspace.R

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imcIcon: ImageView = view.findViewById(R.id.image_view_item_bmi)
        val caloriesIcon: ImageView = view.findViewById(R.id.image_view_item_calories)
        val goalsIcon: ImageView = view.findViewById(R.id.image_view_item_goal)
        val stretchIcon: ImageView = view.findViewById(R.id.image_view_item_stretch)
        val routineIcon: ImageView = view.findViewById(R.id.image_view_item_routine)
        val tipsIcon: ImageView = view.findViewById(R.id.image_view_item_tips)

        // Verifica o tema atual e muda o ícone do ImageView
        if (isDarkTheme()) {
            imcIcon.setImageResource(R.drawable.bmi_dark_mode)
            caloriesIcon.setImageResource(R.drawable.calorias_dark_mode)
            goalsIcon.setImageResource(R.drawable.goal_dark_mode)
            stretchIcon.setImageResource(R.drawable.stretch_dark_mode)
            routineIcon.setImageResource(R.drawable.routine_dark_mode)
            tipsIcon.setImageResource(R.drawable.tips_dark_mode)
        } else {
            imcIcon.setImageResource(R.drawable.bmi_light_mode)
            caloriesIcon.setImageResource(R.drawable.caloria_light_mode)
            goalsIcon.setImageResource(R.drawable.goal_light_mode)
            stretchIcon.setImageResource(R.drawable.stretch_light_mode)
            routineIcon.setImageResource(R.drawable.routine_light_mode)
            tipsIcon.setImageResource(R.drawable.tips_light_mode)
        }
    }

    private fun isDarkTheme(): Boolean {
        val nightModeFlags = resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES
    }

        companion object {
            @JvmStatic
            fun newInstance() =
                HomeFragment()
        }
    }