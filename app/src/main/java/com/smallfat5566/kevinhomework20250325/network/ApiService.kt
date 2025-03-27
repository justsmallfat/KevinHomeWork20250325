package com.smallfat5566.kevinhomework20250325.network

import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getAllStockMetrics(): Call<List<StockMetrics>>
}