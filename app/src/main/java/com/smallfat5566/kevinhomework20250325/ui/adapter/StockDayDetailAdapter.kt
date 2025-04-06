package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.RecycleItemStockDayDetailBinding
import com.smallfat5566.kevinhomework20250325.models.StockDayAll
import com.smallfat5566.kevinhomework20250325.utils.StringUtils

class StockDayDetailAdapter(
    onItemClick: (StockDayAll) -> Unit
) : BaseListAdapter<StockDayAll, RecycleItemStockDayDetailBinding>(
    ItemDiffCallback, onItemClick
) {
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): RecycleItemStockDayDetailBinding {
        return RecycleItemStockDayDetailBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: RecycleItemStockDayDetailBinding): BaseViewHolder<StockDayAll, RecycleItemStockDayDetailBinding> {
        return ItemStockDayDetailViewHolder(binding)
    }

    class ItemStockDayDetailViewHolder(
        binding: RecycleItemStockDayDetailBinding
    ) : BaseViewHolder<StockDayAll, RecycleItemStockDayDetailBinding>(binding) {
        override fun bind(item: StockDayAll, onItemClick: (StockDayAll) -> Unit) {
            binding.root.setOnClickListener {
                onItemClick(item)
            }
            binding.codeTextView.text = item.Code
            binding.nameTextView.text = item.Name



            binding.openingPriceTitleValueView.setValueText(item.OpeningPrice)
            binding.openingPriceTitleValueView.setValueColor(getCompAVGColor(item.OpeningPrice, item.MonthlyAveragePrice))
            binding.closingPriceTitleValueView.setValueText(item.ClosingPrice)
            binding.closingPriceTitleValueView.setValueColor(getCompAVGColor(item.ClosingPrice, item.MonthlyAveragePrice))
            binding.highestPriceTitleValueView.setValueText(item.HighestPrice)
            binding.lowestPriceTitleValueView.setValueText(item.LowestPrice)
            if (StringUtils.checkStringDouble(item.Change)){
                val changeValue = item.Change.toDouble()
                var color = ContextCompat.getColor(binding.root.context, R.color.text_color)
                if (changeValue < 0) {
                    color = ContextCompat.getColor(binding.root.context, R.color.stock_green)
                } else if (changeValue > 0) {
                    color = ContextCompat.getColor(binding.root.context, R.color.stock_red)
                }
                binding.changeTitleValueView.setValueColor(color)
            }
            binding.changeTitleValueView.setValueText(item.Change)
            binding.MonthlyAveragePriceTitleValueView.setValueText(item.MonthlyAveragePrice)
//            binding

            binding.transactionTitleValueView.setValueText(item.Transaction)
            binding.tradeVolumeTitleValueView.setValueText(item.TradeVolume)
            binding.tradeValueTitleValueView.setValueText(item.TradeValue)
        }

        fun getCompAVGColor(prics: String, avg: String?): Int {
            var color = ContextCompat.getColor(binding.root.context, R.color.text_color)
            if (StringUtils.checkStringDouble(prics) && StringUtils.checkStringDouble(avg)) {
                val pricsDouble = prics.toDouble()
                val avgDouble = avg!!.toDouble()
                if (pricsDouble > avgDouble) {
                    color = ContextCompat.getColor(binding.root.context, R.color.stock_red)
                } else if (pricsDouble < avgDouble) {
                    color = ContextCompat.getColor(binding.root.context, R.color.stock_green)
                }
            }
            return color
        }
    }

    companion object {
        private val ItemDiffCallback = object : DiffUtil.ItemCallback<StockDayAll>() {
            override fun areItemsTheSame(oldItem: StockDayAll, newItem: StockDayAll): Boolean {
                return oldItem.Code == newItem.Code
            }

            override fun areContentsTheSame(oldItem: StockDayAll, newItem: StockDayAll): Boolean {
                return oldItem == newItem
            }
        }
    }
}