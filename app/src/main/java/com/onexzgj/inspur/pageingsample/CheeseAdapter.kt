package com.onexzgj.inspur.pageingsample

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * des：
 * author：onexzgj
 * time：2020/3/18
 */
class CheeseAdapter :
    PagedListAdapter<Cheese, CheeseViewHolder>(object : DiffUtil.ItemCallback<Cheese>() {
        override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
            oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder =
        CheeseViewHolder(parent)

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}