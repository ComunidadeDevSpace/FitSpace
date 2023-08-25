package com.app.fitspace.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.fitspace.data.remote.HealthNews
import com.app.fitspace.data.remote.HealthNewsApiClient
import com.app.fitspace.databinding.FragmentNewsBinding
import com.app.fitspace.presentation.adapter.NewsAdapter

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val apiKey = "4425f78069924e2e92a835ddb610704c"
    private val healthNewsList = mutableListOf<HealthNews>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter(healthNewsList)
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRecyclerView.adapter = newsAdapter

        val newsApiClient = HealthNewsApiClient(apiKey)
        newsApiClient.getHealthNews { newsList, error ->
            if (error != null) {
                // Handle error
            } else {
                requireActivity().runOnUiThread {
                    healthNewsList.addAll(newsList ?: emptyList())
                    newsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NewsFragment()
    }
}