package com.onexzgj.inspur.pageingsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.onexzgj.inspur.pageingsample.lifecycle.LifecycleActivity
import com.onexzgj.inspur.pageingsample.livedata.LiveDataActivity
import com.onexzgj.inspur.pageingsample.livedata.MergerLiveDataActivity
import com.onexzgj.inspur.pageingsample.livedata.SwitchMapActivity
import com.onexzgj.inspur.pageingsample.paging.NetPagingActivity
import com.onexzgj.inspur.pageingsample.room.CheeseAdapter
import com.onexzgj.inspur.pageingsample.room.CheeseDb
import com.onexzgj.inspur.pageingsample.room.CheeseViewModel
import com.onexzgj.inspur.pageingsample.viewmodel.ViewModelActivity
import com.onexzgj.inspur.pageingsample.workmanager.WorkManagerActivity
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

        btn_merger.setOnClickListener {
            startActivity(Intent(this, MergerLiveDataActivity::class.java))
        }

        btn_view_model.setOnClickListener {
            startActivity(Intent(this, ViewModelActivity::class.java))
        }

        btn_work_manager.setOnClickListener {
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }


        val adapter = CheeseAdapter()
        rv_am_list.adapter = adapter

//        viewModel.allCheese.observe(this, Observer(adapter::submitList))

        viewModel.allCheese.observe(this, Observer {
            adapter.submitList(it)
        })


        val cheeseAndUser = CheeseDb.get(this).cheeseAndUserDao().getCheeseAndUser()

        Log.d("DATA", "" + cheeseAndUser.size)


        val cheeseAndUsers = CheeseDb.get(this).cheeseAndUsersDao().getCheeseAndUsers()

        Log.d("DATAS", "" + cheeseAndUsers.size)



    }
}
