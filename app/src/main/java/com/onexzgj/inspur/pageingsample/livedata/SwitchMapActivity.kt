package com.onexzgj.inspur.pageingsample.livedata

import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Function
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.onexzgj.inspur.pageingsample.R
import java.util.*


class SwitchMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_map)

        setTitle("LiveData的SwitchMap()的使用")

        var mutableLiveData1: LiveData<String> = MutableLiveData<String>()
        var mutableLiveData2: LiveData<String> = MutableLiveData<String>()
        var liveDataSwitch = MutableLiveData<Boolean>() //1


        val transformedLiveData =
            Transformations.switchMap(liveDataSwitch, object : Function<Boolean, LiveData<String>> {
                override fun apply(input: Boolean?): LiveData<String> {
                    if (input!!) {
                        return mutableLiveData1
                    } else {
                        return mutableLiveData2
                    }
                }
            })


        transformedLiveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.d("TAG", "onChanged:$t")
            }
        })


        liveDataSwitch.postValue(false) //2

        (mutableLiveData1 as MutableLiveData).postValue("Onexzgj的SwitchMap")
        (mutableLiveData2 as MutableLiveData).postValue("OnexZgj的Lifecycle")

    }
}
