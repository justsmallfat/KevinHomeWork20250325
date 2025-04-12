package com.smallfat5566.kevinhomework20250325.ui.activity

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.smallfat5566.kevinhomework20250325.databinding.ActivityMainBinding

class MainActivity : AbstractActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    // 啟用 Edge-to-Edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 處理系統欄的 Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = systemBars.top, // 狀態列高度
                bottom = systemBars.bottom // 導航列高度
            )
            insets // 返回 insets 以便其他視圖處理
        }
    }
}