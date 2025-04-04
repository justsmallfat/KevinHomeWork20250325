package com.smallfat5566.kevinhomework20250325.ui.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.ViewPriceDataBinding
import com.smallfat5566.kevinhomework20250325.utils.StringUtils
import java.text.DecimalFormat

class PriceDataView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewPriceDataBinding

    init {
        // 載入 layout
        binding = ViewPriceDataBinding.inflate(LayoutInflater.from(context), this, true)

        // 解析 XML 屬性
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.PriceDataView)
            val keyText = typedArray.getString(R.styleable.PriceDataView_nameText) ?: ""
            val valueText = typedArray.getString(R.styleable.PriceDataView_valueText) ?: ""

            setNameText(keyText)
            setValueText(valueText)

            setValueTextWidth()

            typedArray.recycle()
        }
    }

    fun setNameText(text: String) {
        binding.tvName.text = text
    }

    fun setValueText(text: String?) {
        if (StringUtils.checkStringDouble(text)){
            val formatter = DecimalFormat("#,###.##")
            val formattedNumber = formatter.format(text?.toDouble())
            binding.tvValue.text = formattedNumber
        }else{
            binding.tvValue.text = "---"
        }
    }
    fun setValueTextWidth() {
        binding.root.post {
            val parentWidth = binding.root.width
            val tvNameWidth = binding.tvName.width
            // 6 margin
            val tvValueWidth = parentWidth - tvNameWidth - 8
            binding.tvValue.width = tvValueWidth
            binding.tvValue.ellipsize = TextUtils.TruncateAt.END
        }
    }
    fun setValueColor(color: Int) {
        binding.tvValue.setTextColor(color)
    }
}
