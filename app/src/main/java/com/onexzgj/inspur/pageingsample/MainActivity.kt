package com.onexzgj.inspur.pageingsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.onexzgj.inspur.pageingsample.lifecycle.LifecycleActivity
import com.onexzgj.inspur.pageingsample.livedata.LiveDataActivity
import com.onexzgj.inspur.pageingsample.livedata.SwitchMapActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<CheeseViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_net.setOnClickListener {
            //            getArticle()
            startActivity(Intent(this, NetPagingActivity::class.java))
        }

        btn_lifecycle.setOnClickListener {
            startActivity(Intent(this, LifecycleActivity::class.java))
        }

        btn_livedata.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }

        btn_switch_map.setOnClickListener {
            startActivity(Intent(this, SwitchMapActivity::class.java))
        }


        val adapter = CheeseAdapter()
        rv_am_list.adapter = adapter

//        viewModel.allCheese.observe(this, Observer(adapter::submitList))

        viewModel.allCheese.observe(this, Observer {
            adapter.submitList(it)
        })


    }
}
