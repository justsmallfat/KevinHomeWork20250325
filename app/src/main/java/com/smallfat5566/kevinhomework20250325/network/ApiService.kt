package com.smallfat5566.kevinhomework20250325.network

import com.smallfat5566.kevinhomework20250325.utils.AppConstant
import com.smallfat5566.kevinhomework20250325.utils.AppConstant.RunMode.DebugMode

interface ApiService {
    private val TAG: String
        get() = this.javaClass.simpleName
    val domainName: String
        get() = if (DebugMode == 1) AppConstant.API.Domain_Dev else AppConstant.API.Domain_Prod
    val protocol: String
        get() = if (DebugMode == 1) AppConstant.API.Protocol_Dev else AppConstant.API.Protocol_Prod
    val baseURL: String
        get() = protocol + domainName


}