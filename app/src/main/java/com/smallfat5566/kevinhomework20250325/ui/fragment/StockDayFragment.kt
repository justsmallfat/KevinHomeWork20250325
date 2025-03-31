package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.FragmentStockDayBinding
import com.smallfat5566.kevinhomework20250325.ui.adapter.StockDayDetailAdapter
import com.smallfat5566.kevinhomework20250325.ui.adapter.StockMetricsAdapter

class StockDayFragment : AbstractFragment() {

    companion object {
        fun newInstance() = StockDayFragment()
    }

    private lateinit var viewModel: StockDayViewModel

    private var _binding: FragmentStockDayBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {



        _binding = FragmentStockDayBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val stockDayRecyclerView: RecyclerView = binding.stockDayRecyclerView
        // 設置 RecyclerView
        stockDayRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragContext)
            adapter = StockMetricsAdapter { item ->
                Toast.makeText(fragContext, "點擊了: ${item.Name}", Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StockDayViewModel::class.java)


        val adapter = StockDayDetailAdapter { item ->
            Toast.makeText(fragContext, "點擊了: ${item.Name}", Toast.LENGTH_LONG).show()
        }

        binding.stockDayRecyclerView.layoutManager = LinearLayoutManager(fragContext)
        binding.stockDayRecyclerView.adapter = adapter

        viewModel.allStockDayDetails.observe(viewLifecycleOwner) { stockDayDetails ->
            adapter.submitList(stockDayDetails)
        }

        viewModel.fetchStockMetrics(fragContext)
    }

}