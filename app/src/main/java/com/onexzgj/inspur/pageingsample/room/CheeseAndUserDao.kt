package com.onexzgj.inspur.pageingsample.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable

/**
 * des：
 * author：onexzgj
 * time：2020/3/27
 */
@Dao
interface CheeseAndUserDao {

    @Transaction
    @Query("SELECT * FROM Cheese")
    fun getCheeseAndUser(): List<CheeseAndUser>



    @Transaction
    @Query("SELECT * FROM Cheese")
    fun findAll(): Flowable<List<CheeseAndUser>>








}