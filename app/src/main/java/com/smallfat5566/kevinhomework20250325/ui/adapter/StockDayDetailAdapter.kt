package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.smallfat5566.kevinhomework20250325.databinding.RecycleItemStockDayDetailBinding
import com.smallfat5566.kevinhomework20250325.models.StockDayAll

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
            binding.codeTextView.text = item.Code
            binding.nameTextView.text = item.Name


            binding.openingPriceTitleValueView.setValueText(item.OpeningPrice)
            binding.closingPriceTitleValueView.setValueText(item.ClosingPrice)
            binding.highestPriceTitleValueView.setValueText(item.HighestPrice)
            binding.lowestPriceTitleValueView.setValueText(item.LowestPrice)
            binding.changeTitleValueView.setValueText(item.Change)
//            binding

            binding.transactionTitleValueView.setValueText(item.Transaction)
            binding.tradeVolumeTitleValueView.setValueText(item.TradeVolume)
            binding.tradeValueTitleValueView.setValueText(item.TradeValue)
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