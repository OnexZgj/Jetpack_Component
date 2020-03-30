package com.onexzgj.inspur.pageingsample.room

import androidx.room.Embedded
import androidx.room.Relation

/**
 * des：一对多的查询
 * author：onexzgj
 * time：2020/3/27
 */
data class CheeseAndUsers(

    @Embedded val owner: Cheese,
    @Relation(
        parentColumn = "id",
        entityColumn = "temp_id"
    )
    val users: List<User>

)