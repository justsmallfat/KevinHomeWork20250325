package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smallfat5566.kevinhomework20250325.databinding.FragmentStockDayAvgBinding
import com.smallfat5566.kevinhomework20250325.ui.adapter.StockDayAVGAdapter

class StockDayAvgFragment : AbstractFragment() {

    companion object {
        fun newInstance() = StockDayAvgFragment()
    }

    private lateinit var viewModel: StockDayAvgViewModel

    private var _binding: FragmentStockDayAvgBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentStockDayAvgBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StockDayAvgViewModel::class.java)



        val adapter = StockDayAVGAdapter { item ->
            Toast.makeText(fragContext, "點擊了: ${item.Name}", Toast.LENGTH_LONG).show()
        }

        binding.stockDayAVGRecyclerView.layoutManager = LinearLayoutManager(fragContext)
        binding.stockDayAVGRecyclerView.adapter = adapter

        viewModel.allStockDayAvgs.observe(viewLifecycleOwner) { stockDayDetails ->
            adapter.submitList(stockDayDetails)
        }

        viewModel.fetchStockMetrics(fragContext)
    }

}