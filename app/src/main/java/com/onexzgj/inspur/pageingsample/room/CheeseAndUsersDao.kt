package com.onexzgj.inspur.pageingsample.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

/**
 * des：一对多查询
 * author：onexzgj
 * time：2020/3/27
 */
@Dao
interface CheeseAndUsersDao {

    @Transaction
    @Query("SELECT * FROM Cheese")
    fun getCheeseAndUsers(): List<CheeseAndUsers>


}