package com.app.fitspace.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.fitspace.R
import com.app.fitspace.data.local.HealthNews
import com.app.fitspace.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class NewsAdapter(private val newsList: List<HealthNews>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(healthNews: HealthNews) {
                Log.d("NewsAdapter", "Image URL: ${healthNews.imageUrl}")

                binding.titleTextView.text = healthNews.title
                binding.descriptionTextView.text = healthNews.description

                // Load image using Glide or Picasso here
                Glide.with(itemView)
                    .load(healthNews.imageUrl) // Used imageUrl from HealthNews
                    .placeholder(R.drawable.placeholder_news_image) // Placeholder image
                    .into(binding.newsImageView) // ImageView from your layout

                itemView.setOnClickListener {
                    // Handle item click if needed
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NewsViewHolder(binding)
        }

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val currentNews = newsList[position]
            holder.bind(currentNews)
        }

        override fun getItemCount(): Int {
            return newsList.size
        }
    }