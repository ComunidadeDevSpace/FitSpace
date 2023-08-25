package com.app.fitspace.data.remote

import com.app.fitspace.data.local.HealthNews

data class HealthNewsResponse(
    val articles: List<HealthNews>
)
