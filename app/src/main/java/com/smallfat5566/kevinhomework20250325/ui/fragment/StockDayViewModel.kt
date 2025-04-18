package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import com.smallfat5566.kevinhomework20250325.utils.SimpleErrorHandleUtils
import com.smallfat5566.kevinhomework20250325.utils.SingleLiveEvent
import com.smallfat5566.kevinhomework20250325.utils.StringUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockDayViewModel : AbstractViewModel() {
    val allStockDayDetails = MutableLiveData<List<StockDayAll>>().apply {
        value = ArrayList<StockDayAll>()
    }
    var selectStockMetrics = SingleLiveEvent<StockMetrics?>()
    var filterText = MutableLiveData<String?>()

    fun fetchStockDay(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, viewModelScope, true)
            val tempStockDayAll = exchangeReportAPIService.getStockDayAll(context)
            val tempStockDayAvg = exchangeReportAPIService.getStockDayAvg(context)
            val avgMap = tempStockDayAvg.associateBy { it.Code }

            var mergedList = tempStockDayAll.map { all ->
                val avg = avgMap[all.Code]
                if (avg != null) {
                    all.copy(MonthlyAveragePrice = avg.MonthlyAveragePrice)
                } else {
                    all
                }
            }

            if (StringUtils.checkStringHasValue(filterText.value)){
                mergedList = mergedList.filter {
                    it.Code.contains(filterText.value.toString()) or it.Name.contains(filterText.value.toString())
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
    fun filterByCode(context: Context, filterCode: String?) {
        filterText.value = filterCode
        fetchStockDay(context)
    }

    fun fetchStockMetrics(context: Context, inputCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, viewModelScope, true)
            val tempStockMetricsList = exchangeReportAPIService.getStockMetricsALL(context)
            val stockMetrics = tempStockMetricsList.find { sm ->
                sm.Code == inputCode
            }
            if (stockMetrics != null){

                selectStockMetrics.postValue(stockMetrics)
            }else{
                val message = context.resources.getString(R.string.error_message_no_data)
                SimpleErrorHandleUtils.errorSampleHandle(context, message)
            }
        }
    }

}