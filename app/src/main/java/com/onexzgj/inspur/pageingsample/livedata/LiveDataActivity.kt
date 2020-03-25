package com.onexzgj.inspur.pageingsample.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.onexzgj.inspur.pageingsample.R
import kotlinx.android.synthetic.main.activity_live_data.*

/**
 * LiveData的简单使用
 * @author OnexZgj
 */
class LiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        setTitle("LiveData使用")

        //LiveData的简单使用======start
        var liveData: LiveData<String> = MutableLiveData<String>()

        btn_ald_post.setOnClickListener {
            (liveData as MutableLiveData).postValue("有新消息了")
        }

        liveData.observe(this, Observer<String> {
            tv_ald_info.text = it
        })
        //liveData的简单使用======end

        //liveData的Transformations.map()的使用====start

        var changLiveData = Transformations.map(liveData) {
            it + "changed"
        }

        changLiveData.observe(this, Observer<String> {
            tv_ald_info.text = it
        })
        //liveData的Transformations.map()的使用====end


    }
}
