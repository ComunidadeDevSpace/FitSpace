package com.app.fitspace.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.fitspace.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.cardViewItemBmi.setOnClickListener { testMessage(it, "Abrir tela IMC") }
        binding.cardViewItemCalories.setOnClickListener { testMessage(it, "Abrir tela Calorias") }
        binding.cardViewItemGoal.setOnClickListener { testMessage(it, "Abrir tela Objetivos") }
        binding.cardViewItemStretch.setOnClickListener { testMessage(it, "Abrir tela Alongamentos") }
        binding.cardViewItemRoutine.setOnClickListener { testMessage(it, "Abrir tela Rotinas Diárias") }
        binding.cardViewItemDica.setOnClickListener { testMessage(it, "Abrir tela Dicas de Saúde") }
    }

    private fun testMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }
}