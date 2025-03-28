package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smallfat5566.kevinhomework20250325.databinding.FragmentStockMetricsBinding
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import com.smallfat5566.kevinhomework20250325.ui.adapter.StockMetricsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockMetricsFragment : AbstractFragment() {

    companion object {
        fun newInstance() = StockMetricsFragment()
    }

    private lateinit var viewModel: StockMetricsViewModel


    private var _binding: FragmentStockMetricsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentStockMetricsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val metricsRecyclerView: RecyclerView = binding.metricsRecyclerView
        // 設置 RecyclerView
        metricsRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragContext)
            adapter = StockMetricsAdapter { item ->
                Toast.makeText(fragContext, "點擊了: ${item.Name}", Toast.LENGTH_LONG).show()
            }
        }

        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StockMetricsViewModel::class.java)
        viewModel.allStockMetrics.observe(viewLifecycleOwner) {
//            textView.text = it
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(fragContext,true).initApiService()
            val allStockMetrics = exchangeReportAPIService.getAllStockMetrics()
            Log.d(TAG, "allStockMetrics : ${allStockMetrics}")
            lifecycleScope.launch(Dispatchers.Main) {
                (binding.metricsRecyclerView.adapter as StockMetricsAdapter).submitList(allStockMetrics)
            }
        }
    }

}