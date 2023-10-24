package com.app.fitspace.presentation.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.app.fitspace.R
import com.app.fitspace.databinding.FragmentHomeBinding
import com.app.fitspace.databinding.FragmentSettingsBinding
import com.app.fitspace.presentation.view.SignUp
import kotlin.system.exitProcess

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null
    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val rootView = binding?.root

        binding?.apply {
            cardViewItemProfile.setOnClickListener {
                val intent = Intent(requireActivity(), SignUp::class.java)
                startActivity(intent)
            }

            cardViewItemInfo.setOnClickListener {
                showAlertDialogAbout()
            }

            cardViewItemLogout.setOnClickListener {
                showAlertDialog()
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            if (isDarkTheme()) {
                imageViewItemProfile.setImageResource(R.drawable.edit_dark_mode)
                imageViewItemNotifications.setImageResource(R.drawable.notification_dark_mode)
                imageViewItemLanguage.setImageResource(R.drawable.language_dark_mode)
                imageViewItemDarkMode.setImageResource(R.drawable.dark_mode_dark_mode)
                imageViewItemLogout.setImageResource(R.drawable.logout_dark_mode)
                imageViewItemInfo.setImageResource(R.drawable.info_dark_mode)
            } else {
                imageViewItemProfile.setImageResource(R.drawable.edit_light_mode)
                imageViewItemNotifications.setImageResource(R.drawable.notification_light_mode)
                imageViewItemLanguage.setImageResource(R.drawable.language_light_mode)
                imageViewItemDarkMode.setImageResource(R.drawable.dark_mode_light_mode)
                imageViewItemLogout.setImageResource(R.drawable.logout_light_mode)
                imageViewItemInfo.setImageResource(R.drawable.info_light_mode)
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
            SettingsFragment()
    }

    private fun showAlertDialogAbout() {
        val build = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_settings_about, null)
        build.setView(view)

        val btnClose = view.findViewById<ImageButton>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }


        dialog = build.create()
        dialog.show()
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Tem certeza que deseja sair?")
            .setMessage("Você precisará logar novamente")
            .setPositiveButton("Sim") { _, _ ->
                exitProcess(0)
            }
            .setNegativeButton("Não") { _, _ -> }
            .create()
        alertDialog.show()
    }
}