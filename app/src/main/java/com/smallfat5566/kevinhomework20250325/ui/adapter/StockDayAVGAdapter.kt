package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.smallfat5566.kevinhomework20250325.databinding.RecycleItemStockDayAvgBinding
import com.smallfat5566.kevinhomework20250325.databinding.RecycleItemStockDayDetailBinding
import com.smallfat5566.kevinhomework20250325.models.StockDayAvg


// Item 佈局的 View Binding
class StockDayAVGAdapter(
    onItemClick: (StockDayAvg) -> Unit
) : BaseListAdapter<StockDayAvg, RecycleItemStockDayAvgBinding>(
    ItemDiffCallback, onItemClick
) {
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): RecycleItemStockDayAvgBinding {
        return RecycleItemStockDayAvgBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: RecycleItemStockDayAvgBinding): BaseViewHolder<StockDayAvg, RecycleItemStockDayAvgBinding> {
        return ItemStockDayAvgViewHolder(binding)
    }

    class ItemStockDayAvgViewHolder(
        binding: RecycleItemStockDayAvgBinding
    ) : BaseViewHolder<StockDayAvg, RecycleItemStockDayAvgBinding>(binding) {
        override fun bind(item: StockDayAvg, onItemClick: (StockDayAvg) -> Unit) {
            binding.titleTextView.text = item.Name
        }
    }

    companion object {
        private val ItemDiffCallback = object : DiffUtil.ItemCallback<StockDayAvg>() {
            override fun areItemsTheSame(oldItem: StockDayAvg, newItem: StockDayAvg): Boolean {
                return oldItem.Code == newItem.Code
            }

            override fun areContentsTheSame(oldItem: StockDayAvg, newItem: StockDayAvg): Boolean {
                return oldItem == newItem
            }
        }
    }
}