package com.onexzgj.inspur.pageingsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * des：
 * author：onexzgj
 * time：2020/3/25
 */

class MyViewModel : ViewModel() {
    private var users: MutableLiveData<String>? = null

    fun getUsers(): LiveData<String>? {
        if (users == null) {
            users = MutableLiveData<String>()
            setName()
        }
        return users
    }

    fun setName() {
        users?.value = "onexzgj"
    }
}