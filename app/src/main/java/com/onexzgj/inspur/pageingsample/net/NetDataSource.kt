package com.onexzgj.inspur.pageingsample.net

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.onexzgj.inspur.onexkt.model.Article
import io.reactivex.schedulers.Schedulers

/**
 * des：
 * author：onexzgj
 * time：2020/3/18
 */
class NetDataSource : PageKeyedDataSource<Int, Article>() {

    var pageNo = 0

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        RedditApi.create().getArticles(pageNo)
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.data?.datas?.let { it1 ->
                    callback.onResult(it1, pageNo, it.data?.curPage)
                }
                pageNo = it.data?.curPage!!
            }
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        RedditApi.create().getArticles(pageNo)
            .subscribeOn(Schedulers.io())
            .subscribe {
                callback.onResult(it.data?.datas!!, it.data?.curPage)
                pageNo = it.data?.curPage!!
            }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

}