package com.onexzgj.inspur.pageingsample.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.onexzgj.inspur.pageingsample.room.CheeseDb

/**
 * des：
 * author：onexzgj
 * time：2020/3/18
 */
class CheeseViewModel(app: Application) : AndroidViewModel(app) {
    val dao = CheeseDb.get(app).cheeseDao()

    val allCheese = dao.findAllCheese().toLiveData(
        Config(
            pageSize = 30,
            enablePlaceholders = true,
            maxSize = 200
        )
    )




}