package com.smallfat5566.kevinhomework20250325.ui.dialog

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.smallfat5566.kevinhomework20250325.MyApplication
import kotlin.math.roundToInt


var Dialog_Layout_small_Rate = 0.55
var Dialog_Layout_simple_Rate = 0.7
var Dialog_Layout_normal_Rate = 0.85
var Dialog_Layout_big_Rate = 1.0

abstract class AbstractDialog(
    var dialogContext: Context,
    val layoutRate: Double = Dialog_Layout_normal_Rate
) : DialogFragment(){
    var TAG = this.javaClass.name
    lateinit var myApplication : MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myApplication = dialogContext.applicationContext as MyApplication
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val metrics = Resources.getSystem().displayMetrics
            val width = (metrics.widthPixels * layoutRate).toInt()
            val height = (metrics.heightPixels * layoutRate).toInt()
            window.setLayout(width, height)
        }
    }
}