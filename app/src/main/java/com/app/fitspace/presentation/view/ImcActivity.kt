package com.app.fitspace.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.fitspace.data.db.AppDataBase
import com.app.fitspace.databinding.ActivityImcBinding
import com.app.fitspace.presentation.viewmodel.UserGoalsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImcBinding

    private val viewModel: UserGoalsViewModel by viewModels {
        UserGoalsViewModel.getVmFactory(application)
    }

    private lateinit var db: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDataBase.getInstance(this)

        lifecycleScope.launch {
            val height = withContext(Dispatchers.IO) {
                db.userGoalsDao().getLastUserHeight()
            }

            val weight = withContext(Dispatchers.IO) {
                db.userGoalsDao().getLastUserWeight()
            }

            if (height > 0 && weight > 0) {
                val finalHeight = height * height
                Log.e("TAGY", height.toString())
                Log.e("TAGY", weight.toString())
                val calculo = weight / finalHeight
                binding.textViewImcResult.text = calculo.toString()

                var classificacao = ""

                if (calculo >= 18.5) {
                    when (calculo) {
                        in 18.5..24.9 -> {
                            classificacao = "Normal"
                        }

                        in 25.0..29.9 -> {
                            classificacao = "Sobrepeso I"

                        }

                        in 30.0..39.9 -> {
                            classificacao = "Obesidade"
                        }

                        else -> {
                            classificacao = "Obesidade Grave"
                        }
                    }
                } else {
                    classificacao = "Abaixo do peso"
                }
                binding.textViewClassificacao.text = classificacao

            } else {
                binding.textViewImcResult.text = "Invalid data"
            }

        }

        binding.apply {
            textViewTabela.setOnClickListener {
                if (informationCard.visibility == View.VISIBLE) {
                    informationCard.visibility = View.INVISIBLE
                    arrowDown.visibility = View.INVISIBLE
                    arrowUp.visibility = View.VISIBLE
                } else {
                    informationCard.visibility = View.VISIBLE
                    arrowDown.visibility = View.VISIBLE
                    arrowUp.visibility = View.INVISIBLE
                }
            }
        }
    }
}