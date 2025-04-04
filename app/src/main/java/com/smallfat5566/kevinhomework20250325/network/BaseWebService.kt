package com.smallfat5566.kevinhomework20250325.network

import android.content.Context
import android.net.http.NetworkException
import com.google.gson.Gson
import com.smallfat5566.kevinhomework20250325.MyApplication
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Domain_Dev
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Domain_Prod
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Protocol_Dev
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Protocol_Prod
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.RunMode.DebugMode
import com.smallfat5566.kevinhomework20250325.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

object WebServiceConfig {
    const val CONNECT_TIMEOUT = 5000  // 毫秒
    const val READ_TIMEOUT = 10000    // 毫秒
}

abstract class BaseWebService(
    context: Context,
    var showProgressDialog: Boolean = true,
    var webServiceContext: Context = context,
) {
    private val TAG = this.javaClass.simpleName
    val domainName = if (DebugMode == 1) Domain_Dev else Domain_Prod
    val protocol = if (DebugMode == 1) Protocol_Dev else Protocol_Prod
    val baseURL = protocol + domainName
    private val myApplication = webServiceContext.applicationContext as MyApplication

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



//    suspend fun postConnection(
//        postDataParams: HashMap<String, Any?>,
//        urlString: String,
//        accept: String = "application/json",
//        requestMethod: String = "POST",
//        contentType: String = "application/json; charset=UTF-8"
//    ): String = withContext(Dispatchers.IO) {
//        if (!NetworkUtils.isNetworkAvailable(webServiceContext)) {
//            throw NetworkException()
//        }
//
//        val url = "$domainName$urlString"
//        val jsonBody = Gson().toJson(postDataParams)
//        val requestBody = RequestBody.create(contentType.toMediaTypeOrNull(), jsonBody)
//
//        val request = Request.Builder()
//            .url(url)
//            .addHeader("Accept", accept)
//            .addHeader("Content-Type", contentType)
//            .method(requestMethod, requestBody)
//            .build()
//
//        Log.d(TAG, "Request URL: $url")
//        Log.d(TAG, "Request Body: $jsonBody")
//
//        try {
//            client.newCall(request).execute().use { response ->
//                if (!response.isSuccessful) {
//                    throw NetworkException(
//                        code = response.code,
//                        message = "Response Code ${response.code} 錯誤 ${response.message}"
//                    )
//                }
//                response.body?.string() ?: throw NetworkException(WebService.Response_Code_Error, "Empty Response")
//            }
//        } catch (e: IOException) {
//            throw NetworkException(WebService.Unknown_Host_Exception_Error, "Network Error: ${e.message}")
//        }
//
//        return@withContext
//    }



    suspend fun fetchData(): String? {
        val client = OkHttpClient()
        return withContext(Dispatchers.IO) {
            val request = Request.Builder().url("https://jsonplaceholder.typicode.com/posts/1").build()
            val response = client.newCall(request).execute()
            response.body?.string()
        }
    }


//
//    @Throws(Exception::class)
//    suspend fun postConnection(
//        postDataParams: java.util.HashMap<String,
//                Any?>, urlString: String,
//        accept: String = "application/json",
//        requestMethod: String = "POST",
//        contentType: String = "application/json; charset=UTF-8"): String = withContext(Dispatchers.IO)  {
//        var response = ""
//
//        Log.d(TAG, "urlString : $urlString")
//        Log.d(TAG, "postDataParams : $postDataParams")
//
//
//
//        if (checkNetworkConnected()) {
//            try {
//                val url = URL(urlString)
//                val conn = url.openConnection() as HttpURLConnection
//                try {
//                    conn.setConnectTimeout(WebService.CONNECT_TIME_OUT);
//                    conn.setReadTimeout(WebService.Read_TIME_OUT);
//                    conn.requestMethod = requestMethod
//                    conn.doInput = true
//                    conn.doOutput = true
//                    conn.setRequestProperty("charset", "utf-8")
//                    conn.setRequestProperty("Accept", accept);
//                    conn.setRequestProperty("Content-Type", contentType);
//                    val outputStream = DataOutputStream(conn?.outputStream)
//
//                    val gson = Gson()
//                    val jsonString = gson.toJson(postDataParams)
////            Log.d(TAG, "jsonString : $jsonString")
//                    outputStream.write(jsonString.toByteArray(StandardCharsets.UTF_8))
//                    outputStream.flush()
//                    val responseCode = conn.responseCode
//                    if (responseCode == HttpsURLConnection.HTTP_OK) {
//                        //解析資料
//                        val inputStream = conn?.inputStream
//                        val reader = BufferedReader(inputStream?.reader())
//                        val content = StringBuilder()
//                        var line = reader.readLine()
//                        while (line != null) {
//                            content.append(line)
//                            line = reader.readLine()
//                        }
//                        reader.close()
//                        response = content.toString()
//                        Log.d(TAG, "response : $response")
//                    } else {
//                        throw NetworkException(code = Response_Code_Error, "Response Code ${conn.responseCode} 錯誤 ${conn.responseMessage}")
//                    }
//                } catch (connectException: ConnectException) {
//                    throw NetworkException(code = Connect_Exception_Error, "Connect Exception ${connectException}")
//                } catch (connectException: SocketException) {
//                    throw NetworkException(code = Socket_Exception, "Socket Exception ${connectException}")
//                } catch (connectException: SocketTimeoutException) {
//                    throw NetworkException(code = Socket_Timeout_Exception_Error, "Socket Timeout Exception ${connectException}")
//                } catch (connectException: UnknownHostException) {
//                    throw NetworkException(code = Unknown_Host_Exception_Error, "Unknown Host Exception ${connectException}")
//                } catch (connectException: SSLHandshakeException) {
//                    throw NetworkException(code = SSL_Handshake_Exception_Error, "SSL Handshake Exception ${connectException}")
//                } catch (e: Exception) {
//                    throw e
//                }
//            } catch (malformedURLException: MalformedURLException) {
//                throw NetworkException(code = Url_Fail, "URL 錯誤")
//            }
//        } else {
//            throw NetworkException(code = No_Network, "無網路")
//        }
//        //隱藏對話方塊
//        if (showProgressDialog) {
////            handler.sendEmptyMessage(1)
//        }
//        return@withContext response
//    }

//    private fun handleNetworkException(e: Exception) {
//        when (e) {
//            is ConnectException -> throw NetworkException(code = Connect_Exception_Error, "Connect Exception $e")
//            is SocketException -> throw NetworkException(code = Socket_Exception, "Socket Exception $e")
//            is SocketTimeoutException -> throw NetworkException(code = Socket_Timeout_Exception_Error, "Socket Timeout Exception $e")
//            is UnknownHostException -> throw NetworkException(code = Unknown_Host_Exception_Error, "Unknown Host Exception $e")
//            is SSLHandshakeException -> throw NetworkException(code = SSL_Handshake_Exception_Error, "SSL Handshake Exception $e")
//            is MalformedURLException -> throw NetworkException(code = Url_Fail, "URL 錯誤")
//            else -> throw e
//        }
//    }

}