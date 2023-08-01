package com.app.fitspace.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.app.fitspace.R
import kotlinx.android.synthetic.main.activity_goal.*

class Goal: AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        val switchButton = findViewById<Switch>(R.id.swButton) as Switch

        switchButton.setOnClickListener {

            if (switchButton.isChecked){
                tvGoal3.text = "Rotina de Alongamento Ativa!"
            } else {
                tvGoal3.text = "Rotina de Alongamento Desativada!"
            }
        }

        val btnDom: Button = findViewById(R.id.btnDom)
        val btnSeg: Button = findViewById(R.id.btnSeg)
        val btnTer: Button = findViewById(R.id.btnTer)
        val btnQua: Button = findViewById(R.id.btnQua)
        val btnQui: Button = findViewById(R.id.btnQui)
        val btnSex: Button = findViewById(R.id.btnSex)
        val btnSab: Button = findViewById(R.id.btnSab)

        btnDom.setOnClickListener {
            tvGoal3.text = "Alongamento no Domingo!"
        }

        btnSeg.setOnClickListener {
            tvGoal3.text = "Alongamento na Segunda-Feira!"
        }

        btnTer.setOnClickListener {
            tvGoal3.text = "Alongamento na Terça-Feira!"
        }

        btnQua.setOnClickListener {
            tvGoal3.text = "Alongamento na Quarta-Feira!"
        }

        btnQui.setOnClickListener {
            tvGoal3.text = "Alongamento na Quinta-Feira!"
        }

        btnSex.setOnClickListener {
            tvGoal3.text = "Alongamento na Sexta-Feira!"
        }

        btnSab.setOnClickListener {
            tvGoal3.text = "Alongamento no Sabado!"
        }
    }
}