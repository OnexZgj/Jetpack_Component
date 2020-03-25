package com.onexzgj.inspur.pageingsample.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.onexzgj.inspur.pageingsample.R
import com.onexzgj.inspur.pageingsample.widget.ItemHorDecoration
import kotlinx.android.synthetic.main.activity_second.*

class NetPagingActivity : AppCompatActivity() {

    lateinit var mAdapter: ArticleAdapter
    val viewModel: ArticleViewModel by viewModels<ArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        mAdapter = ArticleAdapter()

        rv_as_article.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

//        rv_as_article.addItemDecoration(HorItemHorDecoration(this))
        rv_as_article.addItemDecoration(ItemHorDecoration(this))
        rv_as_article.adapter = mAdapter
        getData()
    }

    private fun getData() {
        viewModel.data.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }
}
