package com.onexzgj.inspur.pageingsample.pagingpro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.onexzgj.inspur.pageingsample.R;
import com.onexzgj.inspur.pageingsample.view.EmptyView;
import com.onexzgj.inspur.pageingsample.viewmodel.MyViewModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PagingProActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private PagingProAdapter adapter;
    private EmptyView emptyView;
    private PaingProViewModel paingProViewModel;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_pro);

        smartRefreshLayout = findViewById(R.id.smart_refresh_layout);
        recyclerView = findViewById(R.id.rv_app_recycleview);
        emptyView = findViewById(R.id.empty_view);

        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);



        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new PagingProAdapter(this);
        recyclerView.setAdapter(adapter);

        paingProViewModel = new ViewModelProvider.NewInstanceFactory().create(PaingProViewModel.class);


        paingProViewModel.getPageData().observe(this, new Observer<PagedList<ResponseArticle.DataBean.Article>>() {
            @Override
            public void onChanged(PagedList<ResponseArticle.DataBean.Article> articles) {
                submitList(articles);
            }
        });

        paingProViewModel.getBoundaryPageData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasData) {
                Log.d("TAG", "onChanged: "+hasData +"getBoundaryPageData");
                finishRefresh(hasData);
            }
        });




    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        paingProViewModel.getDataSource().invalidate();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        Log.d("TAG", "onLoadMore: "+"执行了记载更多");

        final PagedList<ResponseArticle.DataBean.Article> currentList = adapter.getCurrentList();
        if (currentList == null || currentList.size() <= 0) {
            finishRefresh(false);
            return;
        }

        paingProViewModel.loadAfter(paingProViewModel.getmPageIndex(),new PageKeyedDataSource.LoadCallback<Integer, ResponseArticle.DataBean.Article>(){

            @Override
            public void onResult(@NonNull List<ResponseArticle.DataBean.Article> data, @Nullable Integer adjacentPageKey) {
                PagedList.Config config = currentList.getConfig();
                if (data != null && data.size() > 0) {
                    //这里 咱们手动接管 分页数据加载的时候 使用MutableItemKeyedDataSource也是可以的。
                    //                    //由于当且仅当 paging不再帮我们分页的时候，我们才会接管。所以 就不需要ViewModel中创建的DataSource继续工作了，所以使用
                    //                    //MutablePageKeyedDataSource也是可以的
                    MutablePageKeyedDataSource dataSource = new MutablePageKeyedDataSource();

                    //这里要把列表上已经显示的先添加到dataSource.data中
                    //而后把本次分页回来的数据再添加到dataSource.data中
                    dataSource.data.addAll(currentList);
                    dataSource.data.addAll(data);
                    PagedList pagedList = dataSource.buildNewPagedList(config);
                    submitList(pagedList);
                }
            }
        });

    }


    public void submitList(PagedList<ResponseArticle.DataBean.Article> result) {
        //只有当新数据集合大于0 的时候，才调用adapter.submitList
        //否则可能会出现 页面----有数据----->被清空-----空布局
        if (result.size() > 0) {
            adapter.submitList(result);
        }
        finishRefresh(result.size() > 0);
    }

    public void finishRefresh(boolean hasData) {
        Log.d("TAG", "finishRefresh: " +hasData);
        PagedList<ResponseArticle.DataBean.Article> currentList = adapter.getCurrentList();
        hasData = hasData || currentList != null && currentList.size() > 0;
        RefreshState state = smartRefreshLayout.getState();
        if (state.isFooter && state.isOpening) {
            smartRefreshLayout.finishLoadMore();
        } else if (state.isHeader && state.isOpening) {
            smartRefreshLayout.finishRefresh();
        }

        if (hasData) {
            emptyView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }



    @SuppressLint("RestrictedApi")
    void loadData(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://www.wanandroid.com/article/list/" + 1 + "/json").build();

        ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = null;
                    response = client.newCall(request).execute();

                    if (response.isSuccessful()) {

                        ResponseArticle responseArticle = JSON.parseObject(response.body().string(), ResponseArticle.class);

                        Log.d("TAG", "run: " + responseArticle.getData().getDatas().size());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

}
