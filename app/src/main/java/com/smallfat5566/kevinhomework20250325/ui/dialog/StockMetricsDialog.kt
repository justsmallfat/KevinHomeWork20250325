package com.smallfat5566.kevinhomework20250325.ui.dialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smallfat5566.kevinhomework20250325.databinding.DialogStockMetricsBinding
import com.smallfat5566.kevinhomework20250325.models.StockMetrics

class StockMetricsDialog() : AbstractDialog() {
    private var _binding: DialogStockMetricsBinding? = null
    private val binding get() = _binding!!
    var stockMetrics: StockMetrics? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            stockMetrics = arguments?.getParcelable("StockMetrics", StockMetrics::class.java)
        }else{
            stockMetrics = arguments?.getParcelable<StockMetrics>("StockMetrics")
        }

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DialogStockMetricsBinding.inflate(inflater, container, false)
        if (stockMetrics != null){
            binding.codeTextView.text = stockMetrics!!.Code
            binding.nameTextView.text = stockMetrics!!.Name

            binding.priceEarningsTitleValueView.setValueText(stockMetrics!!.PEratio)
            binding.dividendYieldTitleValueView.setValueText(stockMetrics!!.DividendYield)
            binding.priceBookTitleValueView.setValueText(stockMetrics!!.PBratio)
        }

        // 點擊確認按鈕
        binding.closeButton.setOnClickListener { dismiss() }

        return binding.root
    }

    // 靜態工廠方法，用來創建實例並傳遞數據
    companion object {
        fun newInstance(stockMetrics: StockMetrics): StockMetricsDialog {
            val fragment = StockMetricsDialog()
            // 創建 Bundle 並放入數據
            val args = Bundle().apply {
                putParcelable("StockMetrics", stockMetrics)
            }
            fragment.arguments = args // 將 Bundle 設置給 Fragment
            return fragment
        }
    }
}