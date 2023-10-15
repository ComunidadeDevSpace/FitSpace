package com.app.fitspace.presentation.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
            // Iniciando a atividade de edição do perfil
            val intent = Intent(activity, SignUp::class.java)
            startActivity(intent)
        }

        cardViewAbout.setOnClickListener {
            showAlertDialogAbout()
        }

        return rootView
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
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.DialogTheme)
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



