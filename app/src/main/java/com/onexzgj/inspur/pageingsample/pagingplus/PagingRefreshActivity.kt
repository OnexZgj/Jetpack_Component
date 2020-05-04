package com.onexzgj.inspur.pageingsample.pagingplus

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.pageingsample.R
import com.onexzgj.inspur.pageingsample.paging.ArticleAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_refresh_paging.*

class PagingRefreshActivity : AppCompatActivity(), OnRefreshListener, OnLoadMoreListener {

    lateinit var mAdapter: ArticleAdapter
    val viewModel: ArticlePlusViewModel by viewModels<ArticlePlusViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_paging)

        mAdapter = ArticleAdapter()

        recycler_view.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
//        rv_as_article.addItemDecoration(HorItemHorDecoration(this))
        recycler_view.adapter = mAdapter

        refresh_layout.setEnableRefresh(true)
        refresh_layout.setEnableLoadMore(true)
        refresh_layout.setOnRefreshListener(this)
        refresh_layout.setOnLoadMoreListener(this)

        getData()


    }

    private fun getData() {
        viewModel.pageData.observe(this, Observer {
            submitList(it)
        })

        viewModel.boundaryPageData.observe(this, Observer {
            if (it != null) {
                finishRefresh(it)
            }
        })
    }

    private fun submitList(data: PagedList<Article>?) {
        if (data?.size!! > 0)
            mAdapter.submitList(data)


        finishRefresh(data.size > 0)
    }

    /**
     * 判断是否有数据
     */
    private fun finishRefresh(hasData: Boolean) {
        if (hasData) {
            val currentList: PagedList<Article> = mAdapter.currentList as PagedList<Article>
            var tempHasData = hasData || currentList != null && currentList.size > 0
            val state: RefreshState = refresh_layout.state

            if (state.isFooter && state.isOpening) {
                refresh_layout.finishLoadMore()
            } else if (state.isHeader && state.isOpening) {
                refresh_layout.finishRefresh()
            }

            if (tempHasData) {
                empty_view.visibility = View.GONE
            } else {
                empty_view.visibility = View.VISIBLE
            }

        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.getDataSource().invalidate()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        val currentList: PagedList<Article> = mAdapter.currentList as PagedList<Article>
        if (currentList == null || currentList.size <= 0) {
            finishRefresh(false)
            return
        }

//        val feed: Feed? = currentList[adapter.getItemCount() - 1]
//        mViewModel.loadAfter(feed.id, object : ItemKeyedDataSource.LoadCallback<Feed?>() {
//            override fun onResult(data: List<Feed?>) {
//                val config = currentList.getConfig()
//                if (data != null && data.size > 0) { //这里 咱们手动接管 分页数据加载的时候 使用MutableItemKeyedDataSource也是可以的。
////由于当且仅当 paging不再帮我们分页的时候，我们才会接管。所以 就不需要ViewModel中创建的DataSource继续工作了，所以使用
////MutablePageKeyedDataSource也是可以的
//                    val dataSource = MutablePageKeyedDataSource()
//                    //这里要把列表上已经显示的先添加到dataSource.data中
////而后把本次分页回来的数据再添加到dataSource.data中
//                    dataSource.data.addAll(currentList)
//                    dataSource.data.addAll(data)
//                    val pagedList: PagedList<*> = dataSource.buildNewPagedList(config)
//                    submitList(pagedList)
//                }
//            }
//        })
    }
}
