package com.app.fitspace.presentation.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.app.fitspace.R
import com.app.fitspace.presentation.view.SignUp
import kotlin.system.exitProcess

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val cardViewProfile = rootView.findViewById<CardView>(R.id.card_view_item_profile)

        val imgBtn = rootView.findViewById<CardView>(R.id.card_view_item_logout)

        imgBtn.setOnClickListener {
            showAlertDialog()
        }

        cardViewProfile.setOnClickListener {
            // Iniciando a atividade de edição do perfil
            val intent = Intent(activity, SignUp::class.java)
            startActivity(intent)
        }

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment()
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



