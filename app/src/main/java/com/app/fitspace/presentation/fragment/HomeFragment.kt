package com.app.fitspace.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.app.fitspace.R
import com.app.fitspace.presentation.view.GoalsActivity

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goalsButton = view.findViewById<CardView>(R.id.card_view_item_goal)

        goalsButton.setOnClickListener {
            val intent = Intent(requireContext(), GoalsActivity::class.java)
            startActivity(intent)
        }
    }
        companion object {
            @JvmStatic
            fun newInstance() =
                HomeFragment()
        }
    }