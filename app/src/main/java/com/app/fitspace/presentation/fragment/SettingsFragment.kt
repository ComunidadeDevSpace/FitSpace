package com.app.fitspace.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.app.fitspace.R
import com.app.fitspace.presentation.view.SignUp

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val cardViewProfile = rootView.findViewById<CardView>(R.id.card_view_item_profile)

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
}