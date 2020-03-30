package com.onexzgj.inspur.pageingsample.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * des：自定义worker
 * author：onexzgj
 * time：2020/3/26
 */

class UploadWork(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Thread.sleep(5000)
        Log.d("UploadWork", "执行完成了异步任务")
        return Result.success()
    }
}