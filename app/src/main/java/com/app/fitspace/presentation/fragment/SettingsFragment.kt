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
import com.app.fitspace.presentation.view.SignUp
import kotlin.system.exitProcess

class SettingsFragment : Fragment() {

    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val cardViewProfile = rootView.findViewById<CardView>(R.id.card_view_item_profile)
        val cardViewAbout = rootView.findViewById<CardView>(R.id.card_view_item_info)
        val imgBtn = rootView.findViewById<CardView>(R.id.card_view_item_logout)

        imgBtn.setOnClickListener {
            showAlertDialog()
        }

        cardViewProfile.setOnClickListener {
            val intent = Intent(activity, SignUp::class.java)
            startActivity(intent)
        }

        cardViewAbout.setOnClickListener {
            showAlertDialogAbout()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileIcon: ImageView = view.findViewById(R.id.image_view_item_profile)
        val notificationsIcon: ImageView = view.findViewById(R.id.image_view_item_notifications)
        val languageIcon: ImageView = view.findViewById(R.id.image_view_item_language)
        val darkModeIcon: ImageView = view.findViewById(R.id.image_view_item_dark_mode)
        val logoutIcon: ImageView = view.findViewById(R.id.image_view_item_logout)
        val infoIcon: ImageView = view.findViewById(R.id.image_view_item_info)

        if (isDarkTheme()) {
            profileIcon.setImageResource(R.drawable.edit_dark_mode)
            notificationsIcon.setImageResource(R.drawable.notification_dark_mode)
            languageIcon.setImageResource(R.drawable.language_dark_mode)
            darkModeIcon.setImageResource(R.drawable.dark_mode_dark_mode)
            logoutIcon.setImageResource(R.drawable.logout_dark_mode)
            infoIcon.setImageResource(R.drawable.info_dark_mode)
        } else {
            profileIcon.setImageResource(R.drawable.edit_light_mode)
            notificationsIcon.setImageResource(R.drawable.notification_light_mode)
            languageIcon.setImageResource(R.drawable.language_light_mode)
            darkModeIcon.setImageResource(R.drawable.dark_mode_light_mode)
            logoutIcon.setImageResource(R.drawable.logout_light_mode)
            infoIcon.setImageResource(R.drawable.info_light_mode)
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
            SettingsFragment()
    }

    private fun showAlertDialogAbout(){
        val build = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_settings_about, null)
        build.setView(view)

        val btnClose = view.findViewById<ImageButton>(R.id.btnClose)
        btnClose.setOnClickListener{
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