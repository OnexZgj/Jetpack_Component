package com.onexzgj.inspur.pageingsample.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.onexzgj.inspur.pageingsample.R

/**
 * ViewModel的使用
 * @author onexzgj
 */
class ViewModelActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()

    lateinit var viewmodel2: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        setTitle("ViewModel的使用")

        var viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)

        viewmodel2 =  ViewModelProvider(this).get(MyViewModel::class.java)


//        viewmodel2 = viewModelFactory.create(MyViewModel::class.java)

        viewmodel2.getUsers()?.observe(this, Observer {
            Log.d("ViewModel", it)
        })
    }
}
