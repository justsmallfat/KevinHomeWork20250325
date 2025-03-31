package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.smallfat5566.kevinhomework20250325.MyApplication
import com.smallfat5566.kevinhomework20250325.databinding.FragmentStockDayBinding

abstract class AbstractViewModel : ViewModel(){
    var TAG = this.javaClass.name
}