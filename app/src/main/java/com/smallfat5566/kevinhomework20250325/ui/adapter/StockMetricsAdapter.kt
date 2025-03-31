package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.smallfat5566.kevinhomework20250325.databinding.RecycleItemStockMetricsBinding
import com.smallfat5566.kevinhomework20250325.models.StockMetrics

class StockMetricsAdapter(
    onItemClick: (StockMetrics) -> Unit
) : BaseListAdapter<StockMetrics, RecycleItemStockMetricsBinding>(
    ItemDiffCallback, onItemClick
) {
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): RecycleItemStockMetricsBinding {
        return RecycleItemStockMetricsBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: RecycleItemStockMetricsBinding): BaseViewHolder<StockMetrics, RecycleItemStockMetricsBinding> {
        return ItemViewHolder(binding)
    }

    class ItemViewHolder(
        binding: RecycleItemStockMetricsBinding
    ) : BaseViewHolder<StockMetrics, RecycleItemStockMetricsBinding>(binding) {
        override fun bind(item: StockMetrics, onItemClick: (StockMetrics) -> Unit) {
            binding.titleTextView.text = item.Name
        }
    }

    companion object {
        private val ItemDiffCallback = object : DiffUtil.ItemCallback<StockMetrics>() {
            override fun areItemsTheSame(oldItem: StockMetrics, newItem: StockMetrics): Boolean {
                return oldItem.Code == newItem.Code
            }

            override fun areContentsTheSame(oldItem: StockMetrics, newItem: StockMetrics): Boolean {
                return oldItem == newItem
            }
        }
    }
}