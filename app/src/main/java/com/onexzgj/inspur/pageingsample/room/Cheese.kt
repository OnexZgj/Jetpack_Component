package com.onexzgj.inspur.pageingsample.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * des：
 * author：onexzgj
 * time：2020/3/18
 */
@Entity
data class Cheese(
    @PrimaryKey(autoGenerate = true) val id: Int, val name: String
)
