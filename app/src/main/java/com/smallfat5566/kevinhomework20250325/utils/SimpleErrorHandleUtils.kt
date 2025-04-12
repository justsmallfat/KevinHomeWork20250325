package com.smallfat5566.kevinhomework20250325.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonSyntaxException
import com.smallfat5566.kevinhomework20250325.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import java.io.UnsupportedEncodingException
import java.text.DecimalFormat

class SimpleErrorHandleUtils {
    companion object {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        var TAG = this.javaClass.name

        /**
         * 傳文字進來
         */
        private fun messageHandle(context: Context, message : String){
            coroutineScope.launch(Dispatchers.Main) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                Log.e(TAG, "${context.packageName.javaClass.name} message : $message")
            }
        }
        /**
         * 自行判斷
         */
        @JvmStatic fun errorSampleHandle(context: Context, message : String){
            messageHandle(context, message)
        }

//        @JvmStatic fun dataErrorSampleHandle(context: Context){
//            context.resources.getString(R.string.error_message_no_data)
//            messageHandle(context, message)
//        }
        /**
         * response Error
         */
        @JvmStatic fun responseErrorSampleHandle(context: Context, message : String){
            messageHandle(context, message)
        }
        @JvmStatic fun unKnowExceptionHandle(context: Context, unKnowException : Exception){
            coroutineScope.launch(Dispatchers.Main) {
                Toast.makeText(context, unKnowException.message, Toast.LENGTH_LONG).show()
                Log.e(TAG, "${context.packageName.javaClass.name} context.Exception : $unKnowException")
            }
        }

    }
}

//輸入資料錯誤
class InputException(message: String?) : Exception(message) {

}