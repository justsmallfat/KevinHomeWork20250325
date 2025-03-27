package com.smallfat5566.kevinhomework20250325.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager {
    // 設定 OkHttpClient
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // 記錄請求與回應
        .connectTimeout(30, TimeUnit.SECONDS) // 設定連線超時
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // 設定 Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/") // 替換為你的 API 網址
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()) // 自動解析 JSON
        .build()

    // 創建 API 服務
    val apiService = retrofit.create(ApiService::class.java)
}