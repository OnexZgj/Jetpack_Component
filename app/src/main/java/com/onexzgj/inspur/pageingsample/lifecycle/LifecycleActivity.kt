package com.onexzgj.inspur.pageingsample.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.onexzgj.inspur.pageingsample.R

/**
 * Lifecycle使用示例Activity
 * @author onexzgj
 */
class LifecycleActivity : AppCompatActivity() {

    private lateinit var myLocationListener: MyLocationListener
    private lateinit var lastLocationListener: LastLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)


        setTitle("LifeCycle使用")

//        myLocationListener = MyLocationListener(this) {
//            //更新UI
//        }

        lastLocationListener = LastLocationListener()
        lifecycle.addObserver(lastLocationListener)

    }

    override fun onStart() {
        super.onStart()
//        myLocationListener.start()
    }

    override fun onStop() {
        super.onStop()
//        myLocationListener.stop()
    }
}
