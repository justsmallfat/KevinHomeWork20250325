package com.smallfat5566.kevinhomework20250325.utils

import android.util.Log
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class StringUtils {
    companion object {

        private val String_Null = Exception("Input is null")
        private val String_Not_Double = Exception("Input is not double")
        private val TAG = "StringUtils"


        val dashDateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dashTimeFormat: DateFormat = SimpleDateFormat("HH:mm")
        val dashDateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        /**
         * checkStringHasValue
         */
        @JvmStatic fun checkStringHasValue(message : Any?) : Boolean{
            var hasValue = false
            if (message != null) {
                hasValue = message.toString().trim().isNotEmpty()
            }
            Log.d(TAG,"hasValue : ${hasValue}")
            return hasValue
        }

        /**
         * checkStringPhoneNum
         */
        @JvmStatic fun checkStringPhoneNum(number : String?) : Boolean{
            var isMobileNum = false
            if (number != null) {
                if (number.trim().isNotEmpty()){
                    if(number.trim().matches("^0[1,2,9][0-9]{8}".toRegex())){
                        isMobileNum = true
                    }
                }
            }
            return isMobileNum
        }

        /**
         * checkStringInt
         */
        @JvmStatic fun checkStringInt(number : String?) : Boolean{
            var isInt = false
            if (number != null) {
                if (number.trim().isNotEmpty()){
                    val num = number.toIntOrNull()
                    isInt = num != null
                }
            }
            return isInt
        }
        /**
         * checkStringDouble
         */
        @JvmStatic fun checkStringDouble(number : String?) : Boolean{
            var isDouble = false
            if (number != null) {
                if (number.trim().isNotEmpty()){
                    val num = number.toDoubleOrNull()
                    if (num!=null && !num.isNaN()){
                        isDouble = true
                    }
                }
            }
            return isDouble
        }
        /**
         * checkStringPrice
         */
        @JvmStatic fun checkStringPrice(number : String?) : Boolean{
            var isPrice = false
            if (number != null) {
                val newString = number.trim().replace(",","")
                if (newString.isNotEmpty()){
                    val num = number.toIntOrNull()
                    isPrice = num != null
                }
            }
            return isPrice
        }


        @JvmStatic fun stringDtoD(numberString : String?, digits:Int) : String{
            var returnString = ""
            if (checkStringDouble(numberString)) {
                val number = numberString?.toDouble()
                returnString = "%.${digits}f".format(number)
            }
            return returnString
        }
        @JvmStatic fun stringDoubleToPrice(numberString : String?, takeSymbol:Boolean = false) : String{
            var returnString = ""
            if (checkStringDouble(numberString)) {
                var format = DecimalFormat("#,###")
                if (takeSymbol){
                    format = DecimalFormat("\$#,###")
                }
                val number = format.format(numberString?.toDouble())
                returnString = ""+number
            }else{
                returnString = ""
            }

            return returnString
        }

        @JvmStatic fun stringPriceToDouble(numberString : String?) : String{
            val returnString: String
            if (checkStringHasValue(numberString)) {
                val tempString = numberString!!.trim().replace(",","")
                if (checkStringDouble(tempString)){
                    returnString = tempString
                }else{
                    throw String_Not_Double
                }
            }else{
                throw String_Null
            }
            return returnString
        }

        @JvmStatic fun getMaskName(nameString : String?) : String{
            var returnString = ""
            try {
                if (checkStringHasValue(nameString)) {
                    val tempName = nameString?.trim()
                    if (tempName!!.isNotEmpty()){
                        returnString = tempName[0].toString().padEnd(tempName.length, 'Ｏ')
                    }else{
                        returnString = "未提供"
                    }
                }else{
                    returnString = "未提供"
                }
            }catch (e:Exception){

            }
            return returnString
        }


        @JvmStatic fun getMaskPhone(phoneNumber : String?) : String{
            var returnString = ""
            if (checkStringHasValue(phoneNumber)) {
                if (phoneNumber?.length!! > 3){
                    returnString = phoneNumber.replace(Regex("\\w(?=\\w{3})"), "*")
                }
            }
            return returnString
        }

        @JvmStatic fun stringFilterNull(string : Any?) : String{
            var returnString = ""
            if (checkStringHasValue(string)) {
                returnString = string.toString()
            }else{
                returnString = ""
            }
            return returnString
        }

        @JvmStatic fun stringDateTimeToStringDate(stringDateTime : String) : String{
            var returnString = ""
            try {
                var dateTime = dashDateTimeFormat.parse(stringDateTime)
                returnString = dashDateFormat.format(dateTime)
            }catch (e:java.lang.Exception){
                Log.d(TAG,"Exception $e")
            }

            return returnString
        }

        @JvmStatic fun stringDateTimeToStringTime(stringDateTime : String) : String{
            var returnString = ""
            try {
                var dateTime = dashDateTimeFormat.parse(stringDateTime)
                returnString = dashTimeFormat.format(dateTime)
            }catch (e:java.lang.Exception){
                Log.d(TAG,"Exception $e")
            }

            return returnString
        }
    }
}