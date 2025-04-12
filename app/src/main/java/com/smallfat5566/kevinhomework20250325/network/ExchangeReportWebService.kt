package com.smallfat5566.kevinhomework20250325.network

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.viewModelScope
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockDayAvg
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.utils.NetworkUtils
import com.smallfat5566.kevinhomework20250325.utils.ProgressDialogUtil
import com.smallfat5566.kevinhomework20250325.utils.SimpleErrorHandleUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ExchangeReportWebService (context: Context,
                                val coroutineScope: CoroutineScope,
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
        if (showProgressDialog) {
            coroutineScope.launch(Dispatchers.Main) {
                ProgressDialogUtil.showProgressDialog(context)
            }
        }
        val result: List<T>

        if (NetworkUtils.isNetworkAvailable(context)) {
            val exchangeReportAPIService = ExchangeReportWebService(context, coroutineScope, true).initApiService()
            result = try {
                apiCall(exchangeReportAPIService)
            } catch (e: IOException) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.message ?: e.toString())
                emptyList()
            } catch (e: HttpException) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.message ?: e.toString())
                emptyList()
            } catch (e: Exception) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.message ?: e.toString())
                e.printStackTrace()
                emptyList()
            }
        } else {
            SimpleErrorHandleUtils.errorSampleHandle(context, context.resources.getString(R.string.error_message_no_network))
            result = emptyList()
        }
        coroutineScope.launch(Dispatchers.Main) {
            ProgressDialogUtil.dismiss()
        }
        return@withContext result
    }




    suspend fun getStockMetricsALL(context: Context): List<StockMetrics> =
        fetchStockData(context) { getAllStockMetrics() }
    suspend fun getStockDayAll(context: Context): List<StockDayAll> =
        fetchStockData(context) { getAllStockDayAll() }
    suspend fun getStockDayAvg(context: Context): List<StockDayAvg> =
        fetchStockData(context) { getAllStockDayAvg() }

}