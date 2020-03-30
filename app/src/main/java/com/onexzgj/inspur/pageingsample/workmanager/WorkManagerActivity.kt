package com.onexzgj.inspur.pageingsample.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.onexzgj.inspur.pageingsample.R

class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        var uploadWork = OneTimeWorkRequest.Builder(UploadWork::class.java).build()

        WorkManager.getInstance(this).enqueue(uploadWork)

    }
}
