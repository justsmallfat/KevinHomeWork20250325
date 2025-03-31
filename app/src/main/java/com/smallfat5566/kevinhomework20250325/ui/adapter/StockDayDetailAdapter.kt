package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smallfat5566.kevinhomework20250325.databinding.RecycleItemStockDayDetailBinding
import com.smallfat5566.kevinhomework20250325.models.StockDayAll


// Item 佈局的 View Binding
class ItemStockDayDetailViewHolder(
    private val binding: RecycleItemStockDayDetailBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: StockDayAll, onItemClick: (StockDayAll) -> Unit) {
        binding.titleTextView.text = item.Name
    }
}

// 使用 ListAdapter 的現代 Adapter
class StockDayDetailAdapter(
    private val onItemClick: (StockDayAll) -> Unit
) : ListAdapter<StockDayAll, ItemStockDayDetailViewHolder>(ItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStockDayDetailViewHolder {
        val binding = RecycleItemStockDayDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemStockDayDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemStockDayDetailViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }


    object ItemDiffCallback : DiffUtil.ItemCallback<StockDayAll>() {
        override fun areItemsTheSame(oldItem: StockDayAll, newItem: StockDayAll): Boolean {
            return oldItem.Code == newItem.Code
        }

        override fun areContentsTheSame(oldItem: StockDayAll, newItem: StockDayAll): Boolean {
            return oldItem == newItem
        }
    }
}
