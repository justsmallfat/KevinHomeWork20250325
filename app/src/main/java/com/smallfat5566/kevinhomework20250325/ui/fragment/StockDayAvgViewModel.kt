package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smallfat5566.kevinhomework20250325.models.StockDayAvg
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockDayAvgViewModel : ViewModel() {
    val allStockDayAvgs = MutableLiveData<List<StockDayAvg>>().apply {
        value = ArrayList<StockDayAvg>()
    }

    fun fetchStockMetrics(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, viewModelScope, true)
            allStockDayAvgs.postValue(exchangeReportAPIService.getStockDayAvg(context))
        }
    }
}