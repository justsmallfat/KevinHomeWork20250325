package com.smallfat5566.kevinhomework20250325.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.smallfat5566.kevinhomework20250325.databinding.DialogStockMetricsBinding
import com.smallfat5566.kevinhomework20250325.models.StockMetrics

class StockMetricsDialog(
    context: Context,
    private val stockMetrics: StockMetrics
) : AbstractDialog(context) {
    private var _binding: DialogStockMetricsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StockMetricsDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DialogStockMetricsBinding.inflate(inflater, container, false)
        binding.codeTextView.text = stockMetrics.Code
        binding.nameTextView.text = stockMetrics.Name



//        viewModel.stockMetrics.observe(viewLifecycleOwner) { stockMetrics ->
//            Log.d(TAG, "stockMetrics : ${stockMetrics}")
//            if (stockMetrics != null){
//
//            }else{
//                SimpleErrorHandleUtils.errorSampleHandle(dialogContext, "無本益比、殖利率及股價淨值比資訊")
//                dismiss()
//            }
//        }

        // 點擊確認按鈕
        binding.closeButton.setOnClickListener { dismiss() }

//        viewModel.fetchStockMetrics(dialogContext)

        return binding.root
    }
}