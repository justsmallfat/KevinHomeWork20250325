package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.FragmentStockDayBinding
import com.smallfat5566.kevinhomework20250325.ui.adapter.StockDayDetailAdapter
import com.smallfat5566.kevinhomework20250325.ui.dialog.StockMetricsDialog
import com.smallfat5566.kevinhomework20250325.utils.StringUtils

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StockDayViewModel::class.java)


        val adapter = StockDayDetailAdapter { item ->

            viewModel.fetchStockMetrics(fragContext, item.Code)
        }

        binding.stockDayRecyclerView.layoutManager = LinearLayoutManager(fragContext)
        binding.stockDayRecyclerView.adapter = adapter
        binding.stockDaySwipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchStockDay(fragContext)
            binding.stockDaySwipeRefreshLayout.setRefreshing(false);
        }

        viewModel.allStockDayDetails.observe(viewLifecycleOwner) { stockDayDetails ->
            adapter.submitList(stockDayDetails)
        }

        viewModel.filterText.observe(viewLifecycleOwner) { filterText ->
            val errorMsg = String.format(resources.getString(R.string.keyword_is), filterText)
            binding.filterTextView.text = errorMsg
        }
        viewModel.selectStockMetrics.observe(viewLifecycleOwner) { stockMetrics ->
            if (stockMetrics != null){
                val dialog = StockMetricsDialog.newInstance(stockMetrics)
                dialog.show(parentFragmentManager, "MyInputDialog")
            }
        }


        binding.filterButton.setOnClickListener {
            val text = viewModel.filterText.value
            showOrderBottomSheet(text)
        }

        viewModel.fetchStockDay(fragContext)
    }


    fun showOrderBottomSheet(filterText: String?) {
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

        // 設置搜尋
        val filterEditText = view.findViewById<EditText>(R.id.filterEditText)
        if (StringUtils.checkStringHasValue(filterText)){
            filterEditText.setText(filterText)
        }
        filterEditText.doOnTextChanged { text, start, before, count ->
            viewModel.filterByCode(fragContext, text.toString())
        }

        // 顯示 Bottom Sheet
        dialog.show()
    }
}