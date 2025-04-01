package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockDayViewModel : ViewModel() {
    val allStockDayDetails = MutableLiveData<List<StockDayAll>>().apply {
        value = ArrayList<StockDayAll>()
    }

    fun fetchStockMetrics(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(context, true)
            val tempStockDayAll = exchangeReportAPIService.getStockDayAll(context)
            allStockDayDetails.postValue(tempStockDayAll)
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

}