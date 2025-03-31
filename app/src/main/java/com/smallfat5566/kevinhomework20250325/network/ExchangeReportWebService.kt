package com.smallfat5566.kevinhomework20250325.network

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresExtension
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
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
//    private val url_BWIBBU_ALL = (Protocol + domainName + prefix + "BWIBBU_ALL")
//    private val url_STOCK_DAY_AVG_ALL = (Protocol + domainName + prefix + "STOCK_DAY_AVG_ALL")
//    private val url_STOCK_DAY_ALL = (Protocol + domainName + prefix + "STOCK_DAY_ALL")


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
        val exchangeReportAPIService = ExchangeReportWebService(context, true).initApiService()
        return@withContext try {
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
    }


    suspend fun getStockMetricsALL(context: Context): List<StockMetrics> =
        fetchStockData(context) { getAllStockMetrics() }

    suspend fun getStockDayAll(context: Context): List<StockDayAll> =
        fetchStockData(context) { getAllStockDayAll() }

//    @Throws(Exception::class)
//    suspend fun getStockMetricsALL(): List<StockMetrics>? = withContext(Dispatchers.IO) {
//        var response: List<StockMetrics>? = null
//        val postDataParams = HashMap<String, Any?>()
//
//
//        try {
//
//
//            response = postConnection(postDataParams, url_register)
//        }catch (jsonException: JSONException){
//            SimpleErrorHandleUtils.jsonExceptionHandle(actContext, jsonException)
//        }catch (jsonSyntaxException: JsonSyntaxException){
//            SimpleErrorHandleUtils.jsonSyntaxExceptionHandle(actContext, jsonSyntaxException)
//        }catch (e:Exception) {
//            SimpleErrorHandleUtils.unKnowExceptionHandle(actContext, e)
//        }
//        return@withContext response
//    }
//
//    @Throws(Exception::class)
//    suspend fun login(accountID: String,
//                      password: String): String = withContext(Dispatchers.IO) {
//        var response: String = ""
//        val postDataParams = HashMap<String, Any?>()
//        postDataParams["accountID"] = accountID
//
//        // 密碼MD5
//        val md = MessageDigest.getInstance("MD5")
//        val md5Password = BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
//
//        postDataParams["password"] = md5Password
//        response = postConnection(postDataParams, url_login)
//        return@withContext response
//    }
}