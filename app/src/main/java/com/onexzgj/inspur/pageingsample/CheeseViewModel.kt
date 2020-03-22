package com.onexzgj.inspur.pageingsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData

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