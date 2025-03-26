package com.smallfat5566.kevinhomework20250325.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest

class ExchangeReportWebService (context: Context,
                                showProgressDialog: Boolean = true
) : BaseWebService(context, showProgressDialog){
    private val prefix = "/v1/exchangeReport/"
    private val url_BWIBBU_ALL = (Protocol + domainName + prefix + "BWIBBU_ALL")
    private val url_STOCK_DAY_AVG_ALL = (Protocol + domainName + prefix + "STOCK_DAY_AVG_ALL")
    private val url_STOCK_DAY_ALL = (Protocol + domainName + prefix + "STOCK_DAY_ALL")

    @Throws(Exception::class)
    suspend fun getBWIBBU_ALL(): List<> = withContext(Dispatchers.IO) {
        var response: String = ""
        val postDataParams = HashMap<String, Any?>()
        postDataParams["accountID"] = accountID

        // 密碼MD5
        val md = MessageDigest.getInstance("MD5")
        val md5Password = BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')

        postDataParams["password"] = md5Password
        postDataParams["accountName"] = accountName
        postDataParams["email"] = email
        postDataParams["gender"] = gender
        response = postConnection(postDataParams, url_register)
        return@withContext response
    }

    @Throws(Exception::class)
    suspend fun login(accountID: String,
                      password: String): String = withContext(Dispatchers.IO) {
        var response: String = ""
        val postDataParams = HashMap<String, Any?>()
        postDataParams["accountID"] = accountID

        // 密碼MD5
        val md = MessageDigest.getInstance("MD5")
        val md5Password = BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')

        postDataParams["password"] = md5Password
        response = postConnection(postDataParams, url_login)
        return@withContext response
    }
}