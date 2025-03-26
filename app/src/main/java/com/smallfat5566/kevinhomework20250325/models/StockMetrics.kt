package com.smallfat5566.kevinhomework20250325.models

import android.os.Parcelable

data class StockMetrics (
    val ID: Int,
    val fragmentID: Int,
    var iconResourceID: Int,
    val iconResourceIDSelected: Int,
    val nameID: Int,
): Parcelable
