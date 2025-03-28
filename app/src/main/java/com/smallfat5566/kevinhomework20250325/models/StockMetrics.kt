package com.smallfat5566.kevinhomework20250325.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StockMetrics (
    val Code: String, //股票代號,
    val Name: String, //股票名稱,
    val PEratio: String, //本益比,
    val DividendYield: String, //殖利率(%),
    val PBratio: String, //股價淨值比
): Parcelable