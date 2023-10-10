package com.app.fitspace.presentation.view

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.app.fitspace.R
import com.app.fitspace.presentation.fragment.GraphFragment
import com.app.fitspace.presentation.fragment.HomeFragment
import com.app.fitspace.presentation.fragment.NewsFragment
import com.app.fitspace.presentation.fragment.SettingsFragment
import com.app.fitspace.presentation.viewmodel.ProfilePictureViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager

    private val viewModelPicture: ProfilePictureViewModel by viewModels {
        ProfilePictureViewModel.getVmFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        fragmentManager = supportFragmentManager

        val homeFragment = HomeFragment.newInstance()
        val graphFragment = GraphFragment.newInstance()
        val newsFragment = NewsFragment.newInstance()
        val settingsFragment = SettingsFragment.newInstance()

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, homeFragment)
            setReorderingAllowed(true)
        }

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.home -> homeFragment
                R.id.graph -> graphFragment
                R.id.news -> newsFragment
                R.id.settings -> settingsFragment
                else -> homeFragment
            }

            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, fragment)
                setReorderingAllowed(true)
            }
            true
        }


        val profilePicture = findViewById<ImageView>(R.id.iv_toolbar_main_profile)
        viewModelPicture.setProfileImage(profilePicture)

    }
}