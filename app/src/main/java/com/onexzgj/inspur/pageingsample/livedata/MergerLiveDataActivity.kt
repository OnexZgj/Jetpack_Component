package com.onexzgj.inspur.pageingsample.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.onexzgj.inspur.pageingsample.R


class MergerLiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merger)

        val mutableLiveData1 = MutableLiveData<String>()
        val mutableLiveData2 = MutableLiveData<String>()
        val mutableLiveData3 = MutableLiveData<String>()
        val liveDataMerger: MediatorLiveData<*> = MediatorLiveData<String>()

        liveDataMerger.addSource(mutableLiveData1, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.d("TAG", "onChanged1:$t")
            }
        })

        liveDataMerger.addSource(mutableLiveData2, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.d("TAG", "onChanged2:$t")
            }
        })

        liveDataMerger.addSource(mutableLiveData3, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.d("TAG", "onChanged3:$t")
            }
        })

        liveDataMerger.observe(this, Observer {
            Log.d("TAG", "onChanged:$it");
        })


        mutableLiveData1.postValue("Onexzgj 的Jetpack")
        mutableLiveData2.postValue("Onexzgj 的Jetpack2")
        mutableLiveData3.postValue("Onexzgj 的Jetpack3")
    }
}
