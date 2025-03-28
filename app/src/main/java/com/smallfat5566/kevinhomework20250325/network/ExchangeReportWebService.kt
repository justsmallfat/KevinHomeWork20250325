package com.smallfat5566.kevinhomework20250325.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ExchangeReportWebService (context: Context,
                                showProgressDialog: Boolean = true
) : BaseWebService(context, showProgressDialog){
//    private val url_BWIBBU_ALL = (Protocol + domainName + prefix + "BWIBBU_ALL")
//    private val url_STOCK_DAY_AVG_ALL = (Protocol + domainName + prefix + "STOCK_DAY_AVG_ALL")
//    private val url_STOCK_DAY_ALL = (Protocol + domainName + prefix + "STOCK_DAY_ALL")



    suspend fun initApiService(): ExchangeReportAPIService = withContext(Dispatchers.IO) {
        // 設定 OkHttpClient
//        val loggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }

//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor) // 記錄請求與回應
//            .connectTimeout(30, TimeUnit.SECONDS) // 設定連線超時
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build()

        // 設定 Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // 自動解析 JSON
            .build()

        // 創建 API 服務
        val apiService = retrofit.create(ExchangeReportAPIService::class.java)
        return@withContext apiService
    }


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