package com.onexzgj.inspur.pageingsample.net

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.pageingsample.R

/**
 * des：
 * author：onexzgj
 * time：2020/3/18
 */

class ArticleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
) {

    val nameView = itemView.findViewById<TextView>(R.id.tv_info)

    var article: Article? = null

    fun bindData(article: Article?) {
        this.article = article
        nameView.text = article?.title
    }

}