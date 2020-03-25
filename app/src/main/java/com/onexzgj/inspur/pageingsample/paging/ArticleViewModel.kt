package com.onexzgj.inspur.pageingsample.paging

import androidx.lifecycle.ViewModel

/**
 * des：
 * author：onexzgj
 * time：2020/3/19
 */

class ArticleViewModel : ViewModel() {
    val data = NetRepository().getData()
}