package com.onexzgj.inspur.pageingsample.net

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.onexzgj.inspur.onexkt.model.Article

/**
 * des：
 * author：onexzgj
 * time：2020/3/19
 */
class NetRepository {

    var pageSize = 20
    lateinit var article: LiveData<PagedList<Article>>

    fun getData(): LiveData<PagedList<Article>> {
        val dataSourceFactory = NetDataSourceFactory()
        article = dataSourceFactory.toLiveData(
            config = Config(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSizeHint = pageSize * 2
            )
        )
        return article
    }
}