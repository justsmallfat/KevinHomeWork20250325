package com.smallfat5566.kevinhomework20250325.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.smallfat5566.kevinhomework20250325.MyApplication

abstract class AbstractActivity : AppCompatActivity(){
    var TAG = this.javaClass.name
    lateinit var actContext: Context
    lateinit var myApplication : MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actContext = this
        myApplication = actContext.applicationContext as MyApplication
    }
}