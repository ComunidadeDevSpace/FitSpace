package com.app.fitspace.presentation.view

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.fitspace.R
import com.app.fitspace.databinding.FragmentHomeBinding
import com.app.fitspace.presentation.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        fragmentManager = supportFragmentManager

        // Define o ouvinte para o evento de seleção de item do BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            // Chama o método para trocar o fragmento
            switchFragment(item.itemId)
            true
        }

        // Exibe o fragmento inicial
        switchFragment(R.id.fragment_home)

    }

    private fun switchFragment(itemId: Int) {
        val fragment: Fragment = when (itemId) {
            R.id.fragment_home -> Fragment()
            R.id.fragment_stats -> Fragment()
            else -> Fragment()
        }

        // Realiza a transação do fragmento para exibir o selecionado
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bottom_app_bar, fragment)
        fragmentTransaction.commit()
    }



}