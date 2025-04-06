package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import com.smallfat5566.kevinhomework20250325.utils.SimpleErrorHandleUtils
import com.smallfat5566.kevinhomework20250325.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockDayViewModel : AbstractViewModel() {
    val allStockDayDetails = MutableLiveData<List<StockDayAll>>().apply {
        value = ArrayList<StockDayAll>()
    }
    var selectStockMetrics = SingleLiveEvent<StockMetrics?>()

    fun fetchStockDay(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, true)
            val tempStockDayAll = exchangeReportAPIService.getStockDayAll(context)
            val tempStockDayAvg = exchangeReportAPIService.getStockDayAvg(context)
            val avgMap = tempStockDayAvg.associateBy { it.Code }

            val mergedList = tempStockDayAll.map { all ->
                val avg = avgMap[all.Code]
                if (avg != null) {
                    all.copy(MonthlyAveragePrice = avg.MonthlyAveragePrice)
                } else {
                    all
                }
            }

            allStockDayDetails.postValue(mergedList)
        }
    }
    fun sortDesc() {
        allStockDayDetails.value = allStockDayDetails.value?.sortedByDescending {
            it.Code
        }
    }
    fun sortAsc() {
        allStockDayDetails.value = allStockDayDetails.value?.sortedBy {
            it.Code
        }
    }

    fun fetchStockMetrics(context: Context, inputCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, true)
            val tempStockMetricsList = exchangeReportAPIService.getStockMetricsALL(context)
            val stockMetrics = tempStockMetricsList.find { sm ->
                sm.Code == inputCode
            }
            if (stockMetrics != null){

                selectStockMetrics.postValue(stockMetrics)
            }else{
                SimpleErrorHandleUtils.errorSampleHandle(context, "無本益比、殖利率及股價淨值比資訊")
            }
        }
    }

    fun showStockMetrics(metrics: StockMetrics) {
        selectStockMetrics.value = metrics
    }
}