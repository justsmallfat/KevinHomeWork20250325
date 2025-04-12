package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException

class StockMetricsViewModel : AbstractViewModel() {

    private val _allStockMetrics = MutableLiveData<List<StockMetrics>>().apply {
        value = ArrayList<StockMetrics>()
    }
    val allStockMetrics: LiveData<List<StockMetrics>> = _allStockMetrics

    fun fetchStockMetrics(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, viewModelScope, true)
            _allStockMetrics.postValue(exchangeReportAPIService.getStockMetricsALL(context))
        }
    }
}