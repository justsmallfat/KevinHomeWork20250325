package com.smallfat5566.kevinhomework20250325.network

import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import retrofit2.http.GET

private const val prefix = "/v1/opendata/"
interface OpenDataAPIService : ApiService {
    @GET(prefix + "t187ap46_L_20")
    suspend fun getCompetitionLawLoss(): List<StockMetrics>

}