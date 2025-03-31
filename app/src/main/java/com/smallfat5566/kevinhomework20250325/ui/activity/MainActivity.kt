package com.smallfat5566.kevinhomework20250325.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.ActivityMainBinding
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AbstractActivity() {

    private lateinit var binding: ActivityMainBinding
//    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_metrics,
                R.id.navigation_stock_day,
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


//        lifecycleScope.launch(Dispatchers.IO) {
//            val exchangeReportAPIService = ExchangeReportWebService(actContext,true).initApiService()
//            val allStockMetrics = exchangeReportAPIService.getAllStockMetrics()
//            Log.d(TAG, "allStockMetrics : ${allStockMetrics}")
////            apiService.getAllStockMetrics().enqueue(object : Callback<List<StockMetrics>> {
////                override fun onResponse(call: Call<List<StockMetrics>>, response: Response<List<StockMetrics>>) {
////                    if (response.isSuccessful) {
////                        val posts = response.body()
////                        println("取得的文章數量: ${posts?.size}")
////                    } else {
////                        println("請求失敗: ${response.code()}")
////                    }
////                }
////
////                override fun onFailure(call: Call<List<StockMetrics>>, t: Throwable) {
////                    println("請求錯誤: ${t.message}")
////                }
////            })
//        }

    }
}