package com.smallfat5566.kevinhomework20250325.ui.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


abstract class AbstractRecyclerViewAdapter<T, RH:RecyclerView.ViewHolder, RV:RecyclerView>(
    private var context: Context,
    protected open val itemActionListener: OnItemActionListener? = null,
    private val alternating: Boolean = true,
    private val selectable: Boolean = true,
):RecyclerView.Adapter<RH>(), Filterable {

    var TAG = this.javaClass.name
    protected var allDatas = ArrayList<T>()
    protected var filterDatas = ArrayList<T>()

//    protected var tracker: SelectionTracker<Long>? = null

    protected var selectPosition:Int? = 0
    protected var selectHolder:RH?=null

    val coroutineScope = CoroutineScope(Dispatchers.Main)

    interface OnItemActionListener {
        fun onItemDelete(position: Int)
    }


    init {
        //會讓滑動掛掉
        if (selectable){
            setHasStableIds(true)
        }
    }

    override fun getItemCount(): Int {
        return filterDatas.size
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    open fun update(updateList: ArrayList<T>?) {
        Log.d(TAG, "Size : ${updateList?.size}")
        allDatas.clear()
        if (updateList != null && updateList.size>0){
            allDatas.addAll(updateList)
        }
        selectPosition = null
        selectHolder = null
        filter.filter("")
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
    override fun getFilter(): Filter {
        return customFilter
    }

    protected open val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filterDatas.clear()
            filterDatas.addAll(allDatas)
            val results = FilterResults()
            results.values = filterDatas
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            filterDatas = if (filterResults?.values == null)
                ArrayList()
            else
                filterResults.values as ArrayList<T>
            notifyDataSetChanged()
        }
    }
}