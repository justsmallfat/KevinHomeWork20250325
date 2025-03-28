package com.smallfat5566.kevinhomework20250325.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StockDayAvg (
    val Code: String,		//股票代號
    val Name: String,		//股票名稱
    val ClosingPrice: String,	//收盤價
    val MonthlyAveragePrice: String	//月平均價
): Parcelable