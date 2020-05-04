package com.onexzgj.inspur.pageingsample.pagingplus

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import androidx.paging.PagedList.BoundaryCallback
import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.pageingsample.paging.NetRepository

/**
 * des：
 * author：onexzgj
 * time：2020/3/19
 */

class ArticlePlusViewModel : ViewModel() {
    val data = NetRepository().getData()

    var pageSize = 20

    var boundaryPageData: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>()

    var config = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(40) // .setMaxSize(100)；
        .build()

    var factory: DataSource.Factory<Int, Article> = ArticlePlusDataSourceFactory()




    var callback: BoundaryCallback<Article> = object : BoundaryCallback<Article>() {
        override fun onZeroItemsLoaded() { //新提交的PagedList中没有数据
            boundaryPageData!!.postValue(false)

        }

        override fun onItemAtFrontLoaded(itemAtFront: Article) { //新提交的PagedList中第一条数据被加载到列表上
            boundaryPageData!!.postValue(true)

        }

        override fun onItemAtEndLoaded(itemAtEnd: Article) { //新提交的PagedList中最后一条数据被加载到列表上
        }
    }

//    var pageData=factory.toLiveData(
//        config = Config(
//            pageSize = pageSize,
//            enablePlaceholders = false,
//            initialLoadSizeHint = pageSize * 2
//        )
//    )

    var pageData: LiveData<PagedList<Article>> = LivePagedListBuilder(factory, config)
        .setInitialLoadKey(0) //.setFetchExecutor()
        .setBoundaryCallback(callback)
        .build()


    fun getDataSource():ArticlPlusDataSource{
        return (factory as ArticlePlusDataSourceFactory).source
    }

}