package com.onexzgj.inspur.pageingsample.pagingpro;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * des：
 * author：onexzgj
 * time：2020/5/4
 */
public class PaingProViewModel extends AbsPagingProViewModel<ResponseArticle.DataBean.Article> {

    private AtomicBoolean loadAfter = new AtomicBoolean(false);

    private int mPageIndex = 0;

    public int getmPageIndex() {
        return mPageIndex;
    }

    @Override
    protected DataSource createDataSource() {
        return new ArticleDataSource();
    }


    class ArticleDataSource extends PageKeyedDataSource<Integer, ResponseArticle.DataBean.Article> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ResponseArticle.DataBean.Article> callback) {
            loadData(0, callback, null);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ResponseArticle.DataBean.Article> callback) {
            callback.onResult(Collections.emptyList(), 0);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ResponseArticle.DataBean.Article> callback) {
            loadData(params.key, null, callback);
        }
    }


    @SuppressLint("RestrictedApi")
    private void loadData(int pageIndex, PageKeyedDataSource.LoadInitialCallback<Integer, ResponseArticle.DataBean.Article> initCallback, PageKeyedDataSource.LoadCallback<Integer, ResponseArticle.DataBean.Article> callback) {

        mPageIndex = pageIndex;
        if (pageIndex > 0) {
            loadAfter.set(true);
        }

        Log.d("TAG", "loadData: pageIndex" + pageIndex);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.wanandroid.com/article/list/" + pageIndex + "/json").build();
        try {
            Response response = null;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseArticle responseArticle = JSON.parseObject(response.body().string(), ResponseArticle.class);
                Log.d("TAG", "run: " + responseArticle.getData().getDatas().size());

                if (initCallback != null) {
                    Log.d("TAG", "loadData: initCallback");
                    initCallback.onResult(responseArticle.getData().getDatas(), pageIndex - 1, pageIndex + 1);
                } else {
                    Log.d("TAG", "loadData: callback");
                    callback.onResult(responseArticle.getData().getDatas(), pageIndex + 1);
                }


                if (pageIndex > 0) {
                    //通过BoundaryPageData发送数据 告诉UI层 是否应该主动关闭上拉加载分页的动画
                    ((MutableLiveData) getBoundaryPageData()).postValue(responseArticle.getData().getDatas().size() > 0);

                    loadAfter.set(false);
                }

                mPageIndex = pageIndex + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("RestrictedApi")
    public void loadAfter(int pageIndex, PageKeyedDataSource.LoadCallback<Integer, ResponseArticle.DataBean.Article> callback) {

        Log.d("TAG", "loadAfter: pageIndex" + pageIndex);

        if (loadAfter.get()) {
            callback.onResult(Collections.emptyList(), 0);
            return;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.wanandroid.com/article/list/" + pageIndex + "/json").build();
        ArchTaskExecutor.getIOThreadExecutor().
                execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Response response = null;
                                    response = client.newCall(request).execute();
                                    if (response.isSuccessful()) {
                                        ResponseArticle responseArticle = JSON.parseObject(response.body().string(), ResponseArticle.class);
                                        Log.d("TAG", "run: " + responseArticle.getData().getDatas().size());

                                        Log.d("TAG", "loadData: callback");

                                        callback.onResult(responseArticle.getData().getDatas(), pageIndex + 1);


                                        if (pageIndex > 0) {
                                            //通过BoundaryPageData发送数据 告诉UI层 是否应该主动关闭上拉加载分页的动画
                                            ((MutableLiveData) getBoundaryPageData()).postValue(responseArticle.getData().getDatas().size() > 0);
                                            loadAfter.set(false);
                                        }

                                        mPageIndex = pageIndex + 1;
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
    }

}

