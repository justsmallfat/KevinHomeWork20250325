package com.smallfat5566.kevinhomework20250325.network

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresExtension
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockDayAvg
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.utils.NetworkUtils
import com.smallfat5566.kevinhomework20250325.utils.SimpleErrorHandleUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ExchangeReportWebService (context: Context,
                                showProgressDialog: Boolean = true
) : BaseWebService(context, showProgressDialog){

    fun initApiService(): ExchangeReportAPIService {
        // 設定 Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // 自動解析 JSON
            .build()

        // 創建 API 服務
        return retrofit.create(ExchangeReportAPIService::class.java)
    }

    suspend fun <T> fetchStockData(
        context: Context,
        apiCall: suspend ExchangeReportAPIService.() -> List<T>
    ): List<T> = withContext(Dispatchers.IO) {

        return@withContext if (NetworkUtils.isNetworkAvailable(context)){
            val exchangeReportAPIService = ExchangeReportWebService(context, true).initApiService()
             try {
                apiCall(exchangeReportAPIService)
            } catch (e: IOException) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.toString())
                emptyList()
            } catch (e: HttpException) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.toString())
                emptyList()
            } catch (e: Exception) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.toString())
                e.printStackTrace()
                emptyList()
            }
        }else{
            SimpleErrorHandleUtils.errorSampleHandle(context, "沒網路")
            emptyList()
        }
    }


    suspend fun getStockMetricsALL(context: Context): List<StockMetrics> =
        fetchStockData(context) { getAllStockMetrics() }

    suspend fun getStockDayAll(context: Context): List<StockDayAll> =
        fetchStockData(context) { getAllStockDayAll() }
    suspend fun getStockDayAvg(context: Context): List<StockDayAvg> =
        fetchStockData(context) { getAllStockDayAvg() }

}