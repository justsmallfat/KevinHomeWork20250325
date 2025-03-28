package com.smallfat5566.kevinhomework20250325.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.smallfat5566.kevinhomework20250325.MyApplication

abstract class AbstractFragment : Fragment(){
    var TAG = this.javaClass.name
    lateinit var fragContext: Context
    lateinit var myApplication : MyApplication

    protected lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragContext = requireActivity()
        myApplication = fragContext.applicationContext as MyApplication
        Log.d(TAG, "arguments : "+arguments)
    }
}