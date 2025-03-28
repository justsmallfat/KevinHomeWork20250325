package com.smallfat5566.kevinhomework20250325.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.smallfat5566.kevinhomework20250325.models.StockMetrics
import com.smallfat5566.kevinhomework20250325.network.ApiService
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportAPIService
import com.smallfat5566.kevinhomework20250325.network.ExchangeReportWebService
import com.smallfat5566.kevinhomework20250325.ui.theme.KevinHomeWork20250325Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KevinHomeWork20250325Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }


        lifecycleScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeReportWebService(actContext,true).initApiService()
            val allStockMetrics = exchangeReportAPIService.getAllStockMetrics()
            Log.d(TAG, "allStockMetrics : ${allStockMetrics}")
//            apiService.getAllStockMetrics().enqueue(object : Callback<List<StockMetrics>> {
//                override fun onResponse(call: Call<List<StockMetrics>>, response: Response<List<StockMetrics>>) {
//                    if (response.isSuccessful) {
//                        val posts = response.body()
//                        println("取得的文章數量: ${posts?.size}")
//                    } else {
//                        println("請求失敗: ${response.code()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<List<StockMetrics>>, t: Throwable) {
//                    println("請求錯誤: ${t.message}")
//                }
//            })
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KevinHomeWork20250325Theme {
        Greeting("Android")
    }
}