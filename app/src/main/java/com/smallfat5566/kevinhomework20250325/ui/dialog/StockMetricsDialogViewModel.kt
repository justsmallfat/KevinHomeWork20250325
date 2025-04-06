package com.smallfat5566.kevinhomework20250325.ui.dialog

import androidx.lifecycle.MutableLiveData
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.ui.fragment.AbstractViewModel

class StockMetricsDialogViewModel : AbstractViewModel() {

    var inputCode = String()

    val stockMetrics = MutableLiveData<StockMetrics?>()

}
