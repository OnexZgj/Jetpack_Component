package com.onexzgj.inspur.pageingsample.room

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onexzgj.inspur.pageingsample.R

/**
 * des：
 * author：onexzgj
 * time：2020/3/18
 */

class CheeseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
) {

    val nameView = itemView.findViewById<TextView>(R.id.tv_info)

    var cheese: Cheese? = null

    fun bindData(cheese: Cheese?) {
        this.cheese = cheese
        nameView.text = cheese?.name
    }

}