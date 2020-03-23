package com.onexzgj.inspur.pageingsample.lifecycle

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * des：
 * author：onexzgj
 * time：2020/3/23
 */
class LastLocationListener :LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        // connect to system location service
        Log.d("lifecycle","LastLocationListener start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // disconnect from system location service
        Log.d("lifecycle","LastLocationListener stop")
    }
}

