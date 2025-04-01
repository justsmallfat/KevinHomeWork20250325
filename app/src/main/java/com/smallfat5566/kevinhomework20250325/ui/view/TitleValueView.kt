package com.smallfat5566.kevinhomework20250325.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.ViewTitleValueBinding

class TitleValueView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewTitleValueBinding

    init {
        // 載入 layout
        binding = ViewTitleValueBinding.inflate(LayoutInflater.from(context), this, true)

        // 解析 XML 屬性
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.KeyValueView)
            val keyText = typedArray.getString(R.styleable.KeyValueView_keyText) ?: ""
            val valueText = typedArray.getString(R.styleable.KeyValueView_valueText) ?: ""

            setKeyText(keyText)
            setValueText(valueText)

            typedArray.recycle()
        }
    }

    fun setKeyText(text: String) {
        binding.tvKey.text = text
    }

    fun setValueText(text: String) {
        binding.tvValue.text = text
    }
}
