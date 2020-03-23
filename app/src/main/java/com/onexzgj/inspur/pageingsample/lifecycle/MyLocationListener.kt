package com.onexzgj.inspur.pageingsample.lifecycle

import android.content.Context
import android.location.Location
import android.util.Log

/**
 * des：
 * author：onexzgj
 * time：2020/3/23
 */
class MyLocationListener(
    private val context: Context,
    private val callback: (Location) -> Unit
) {

    fun start() {
        // connect to system location service
        Log.d("lifecycle","MyLocationListener start")
    }

    fun stop() {
        // disconnect from system location service
        Log.d("lifecycle","MyLocationListener stop")
    }
}

