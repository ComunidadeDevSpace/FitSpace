package com.app.fitspace.data.remote

import com.google.gson.Gson
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HealthNewsApiClient(private val apiKey: String) {

    private val client = OkHttpClient()
    private val gson = Gson()

    fun getHealthNews(callback: (List<HealthNews>?, Throwable?) -> Unit) {
        val url = "https://newsapi.org/v2/everything?q=fitness%20AND%20health&apiKey=$apiKey"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response) {
                val responseBody = response.body?.string()
                val newsResponse = gson.fromJson(responseBody, HealthNewsResponse::class.java)
                val newsList = newsResponse.articles
                callback(newsList, null)
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback(null, e)
            }
        })
    }
}
