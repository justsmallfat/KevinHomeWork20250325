package com.smallfat5566.kevinhomework20250325.utils

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.smallfat5566.kevinhomework20250325.R


class ProgressDialogUtil {
    companion object {
        private var loadingDialog: AlertDialog? = null
        private var loadingCounter = 0
        private val lock = Any()

        fun showProgressDialog(context: Context) {
            synchronized(lock) {
                loadingCounter++
                if (loadingCounter == 1) {
                    val builder = AlertDialog.Builder(context)
                    builder.setView(R.layout.dialog_loading)
                    builder.setCancelable(false)
                    loadingDialog = builder.create()
                    loadingDialog?.show()
                }
            }
        }
        /**
         * 隱藏耗時對話方塊
         */
        fun dismiss() {
            synchronized(lock) {
                loadingCounter--
                if (loadingCounter <= 0) {
                    loadingDialog?.dismiss()
                    loadingDialog = null
                    loadingCounter = 0
                }
            }
        }
    }
}