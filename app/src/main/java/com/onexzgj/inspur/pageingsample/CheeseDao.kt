package com.onexzgj.inspur.pageingsample

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * des：
 * author：onexzgj
 * time：2020/3/18
 */
@Dao
interface CheeseDao {

    @Query("select * from cheese order by name ")
    fun findAllCheese(): DataSource.Factory<Int, Cheese>

    @Insert
    fun insert(cheeses: List<Cheese>)

    @Insert
    fun insert(cheese: Cheese)

    @Delete
    fun delete(cheese: Cheese)

}