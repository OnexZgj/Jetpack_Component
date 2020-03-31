package com.onexzgj.inspur.pageingsample.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * des：
 * author：onexzgj
 * time：2020/3/27
 */

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int, val name: String,
    val temp_id: Int, //测试一对多关系设立的ID
    val age: Int,
    val sex: Int
)
