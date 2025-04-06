package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.FragmentStockDayBinding
import com.smallfat5566.kevinhomework20250325.ui.adapter.StockDayDetailAdapter
import com.smallfat5566.kevinhomework20250325.ui.adapter.StockMetricsAdapter
import com.smallfat5566.kevinhomework20250325.ui.dialog.StockMetricsDialog

class StockDayFragment : AbstractFragment() {

    private lateinit var viewModel: StockDayViewModel

    private var _binding: FragmentStockDayBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentStockDayBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StockDayViewModel::class.java)


        val adapter = StockDayDetailAdapter { item ->

            viewModel.fetchStockMetrics(fragContext, item.Code)
        }

        binding.stockDayRecyclerView.layoutManager = LinearLayoutManager(fragContext)
        binding.stockDayRecyclerView.adapter = adapter

        viewModel.allStockDayDetails.observe(viewLifecycleOwner) { stockDayDetails ->
            adapter.submitList(stockDayDetails)
        }

        viewModel.selectStockMetrics.observe(viewLifecycleOwner) { stockMetrics ->
            Log.d(TAG, "stockMetrics : ${stockMetrics}")
            if (stockMetrics != null){
                val dialog = StockMetricsDialog(fragContext, stockMetrics)
                dialog.show(parentFragmentManager, "MyInputDialog")
            }
        }


        binding.filterButton.setOnClickListener {
            showOrderBottomSheet()
        }

        viewModel.fetchStockDay(fragContext)
    }

    fun showOrderBottomSheet() {
        // 創建 Bottom Sheet Dialog
        val dialog = BottomSheetDialog(fragContext)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_stock_day_detail_layout, null)
        dialog.setContentView(view)

        // 設置關閉按鈕
        val closeButton = view.findViewById<ImageButton>(R.id.closeButton)
        closeButton.setOnClickListener {
            dialog.dismiss() // 關閉 Bottom Sheet
        }

        // 設置倒序按鈕
        val sortDescButton = view.findViewById<Button>(R.id.sortDescButton)
        sortDescButton.setOnClickListener {
            viewModel.sortDesc()
            dialog.dismiss() // 關閉 Bottom Sheet
        }
        // 設置順序按鈕
        val sortAscButton = view.findViewById<Button>(R.id.sortAscButton)
        sortAscButton.setOnClickListener {
            viewModel.sortAsc()
            dialog.dismiss() // 關閉 Bottom Sheet
        }

        // 顯示 Bottom Sheet
        dialog.show()
    }
}