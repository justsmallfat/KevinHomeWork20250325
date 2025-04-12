package com.smallfat5566.kevinhomework20250325.network

import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockDayAvg
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import retrofit2.http.GET

private const val prefix = "/v1/exchangeReport/"
interface ExchangeReportAPIService : ApiService{
    @GET(prefix + "BWIBBU_ALL")
//    @GET(prefix + "STOCK_DAY_AVG_ALL")
    suspend fun getAllStockMetrics(): List<StockMetrics>
    @GET(prefix + "STOCK_DAY_AVG_ALL")
    suspend fun getAllStockDayAvg(): List<StockDayAvg>
    @GET(prefix + "STOCK_DAY_ALL")
    suspend fun getAllStockDayAll(): List<StockDayAll>

}