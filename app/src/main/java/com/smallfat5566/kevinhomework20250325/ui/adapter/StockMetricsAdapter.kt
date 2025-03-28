package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smallfat5566.kevinhomework20250325.R
import com.smallfat5566.kevinhomework20250325.databinding.RecycleItemStockMetricsBinding
import com.smallfat5566.kevinhomework20250325.models.StockMetrics


// Item 佈局的 View Binding
class ItemViewHolder(
    private val binding: RecycleItemStockMetricsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: StockMetrics, onItemClick: (StockMetrics) -> Unit) {
        binding.titleTextView.text = item.Name
    }
}

// 使用 ListAdapter 的現代 Adapter
class StockMetricsAdapter(
    private val onItemClick: (StockMetrics) -> Unit
) : ListAdapter<StockMetrics, ItemViewHolder>(ItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RecycleItemStockMetricsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

// DiffUtil 回調，用於高效比較項目
object ItemDiffCallback : DiffUtil.ItemCallback<StockMetrics>() {
    override fun areItemsTheSame(oldItem: StockMetrics, newItem: StockMetrics): Boolean {
        return oldItem.Code == newItem.Code
    }

    override fun areContentsTheSame(oldItem: StockMetrics, newItem: StockMetrics): Boolean {
        return oldItem == newItem
    }
}
//
//
//
//
//
//
//class StockMetricsAdapter(private val items: List<String>) :
//    RecyclerView.Adapter<StockMetricsAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView: TextView = itemView.findViewById(R.id.textView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textView.text = items[position]
//    }
//
//    override fun getItemCount(): Int = items.size
//}