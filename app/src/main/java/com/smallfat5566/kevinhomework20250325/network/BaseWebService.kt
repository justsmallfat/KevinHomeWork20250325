package com.smallfat5566.kevinhomework20250325.network

import android.content.Context
import com.smallfat5566.kevinhomework20250325.MyApplication
import com.smallfat5566.kevinhomework20250325.network.WebServiceConfig.CONNECT_TIMEOUT
import com.smallfat5566.kevinhomework20250325.network.WebServiceConfig.READ_TIMEOUT
import com.smallfat5566.kevinhomework20250325.network.WebServiceConfig.WRITE_TIMEOUT
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Domain_Dev
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Domain_Prod
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Protocol_Dev
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.API.Protocol_Prod
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.RunMode.DebugMode
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object WebServiceConfig {
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}

abstract class BaseWebService(
    val context: Context,
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
//        .addInterceptor(loggingInterceptor) // 記錄請求與回應
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) // 設定連線超時
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

}