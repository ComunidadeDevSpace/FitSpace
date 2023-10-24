package com.app.fitspace.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.fitspace.R
import com.app.fitspace.databinding.FragmentHomeBinding
import com.app.fitspace.databinding.FragmentSettingsBinding
import com.app.fitspace.presentation.view.GoalsActivity
import com.app.fitspace.presentation.view.ImcActivity
import com.app.fitspace.presentation.view.SignUp

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding?.root

        binding?.apply {
            cardViewItemGoal.setOnClickListener {
                val intent = Intent(requireContext(), GoalsActivity::class.java)
                startActivity(intent)
            }

            textViewItemBmi.setOnClickListener {
                val intent = Intent(requireContext(), ImcActivity::class.java)
                startActivity(intent)
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            if (isDarkTheme()) {
                imageViewItemBmi.setImageResource(R.drawable.bmi_dark_mode)
                imageViewItemCalories.setImageResource(R.drawable.calorias_dark_mode)
                imageViewItemGoal.setImageResource(R.drawable.goal_dark_mode)
                imageViewItemStretch.setImageResource(R.drawable.stretch_dark_mode)
                imageViewItemRoutine.setImageResource(R.drawable.routine_dark_mode)
                imageViewItemTips.setImageResource(R.drawable.tips_dark_mode)
            } else {
                imageViewItemBmi.setImageResource(R.drawable.bmi_light_mode)
                imageViewItemCalories.setImageResource(R.drawable.caloria_light_mode)
                imageViewItemGoal.setImageResource(R.drawable.goal_light_mode)
                imageViewItemStretch.setImageResource(R.drawable.stretch_light_mode)
                imageViewItemRoutine.setImageResource(R.drawable.routine_light_mode)
                imageViewItemTips.setImageResource(R.drawable.tips_light_mode)
            }
        }
    }

    private fun isDarkTheme(): Boolean {
        val nightModeFlags = resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}