package com.smallfat5566.kevinhomework20250325.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smallfat5566.kevinhomework20250325.models.StockMetrics

class StockMetricsViewModel : ViewModel() {

    private val _allStockMetrics = MutableLiveData<List<StockMetrics>>().apply {
        value = ArrayList<StockMetrics>()
    }
    val allStockMetrics: LiveData<List<StockMetrics>> = _allStockMetrics
}