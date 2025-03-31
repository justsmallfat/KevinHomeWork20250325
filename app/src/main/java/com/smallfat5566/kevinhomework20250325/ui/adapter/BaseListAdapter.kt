package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T, VB : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val onItemClick: (T) -> Unit
) : ListAdapter<T, BaseListAdapter.BaseViewHolder<T, VB>>(diffCallback) {

    abstract class BaseViewHolder<T, VB : ViewBinding>(
        protected val binding: VB
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T, onItemClick: (T) -> Unit)
    }

    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        val binding = createBinding(LayoutInflater.from(parent.context), parent)
        return createViewHolder(binding)
    }

    abstract fun createViewHolder(binding: VB): BaseViewHolder<T, VB>

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}