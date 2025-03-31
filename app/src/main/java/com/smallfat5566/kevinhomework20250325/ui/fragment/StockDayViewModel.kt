package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockDayViewModel : ViewModel() {
    val allStockDayDetails = MutableLiveData<List<StockDayAll>>().apply {
        value = ArrayList<StockDayAll>()
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun fetchStockMetrics(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, true)
            allStockDayDetails.postValue(exchangeReportAPIService.getStockDayAll(context))
//            val exchangeReportAPIService = ExchangeReportWebService(context, true).initApiService()
//            val result = exchangeReportAPIService.getAllStockDayAll()
//            allStockDayDetails.postValue(result)
        }
    }
}