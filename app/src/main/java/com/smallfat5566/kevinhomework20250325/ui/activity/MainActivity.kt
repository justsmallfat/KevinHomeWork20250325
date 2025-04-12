package com.smallfat5566.kevinhomework20250325.ui.activity

import android.os.Bundle
import com.smallfat5566.kevinhomework20250325.databinding.ActivityMainBinding

class MainActivity : AbstractActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}