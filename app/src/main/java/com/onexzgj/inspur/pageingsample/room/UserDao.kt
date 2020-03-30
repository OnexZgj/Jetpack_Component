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
interface UserDao {

    @Query("select * from user order by name ")
    fun findAllUser(): DataSource.Factory<Int, User>

    @Insert
    fun insert(users: List<User>)

    //这里是更新策略
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

}