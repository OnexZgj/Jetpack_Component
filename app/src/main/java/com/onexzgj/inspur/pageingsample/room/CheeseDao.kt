package com.onexzgj.inspur.pageingsample.room

import androidx.paging.DataSource
import androidx.room.*
import com.onexzgj.inspur.pageingsample.room.Cheese

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

    //这里是更新策略
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cheese: Cheese)

    @Delete
    fun delete(cheese: Cheese)

}