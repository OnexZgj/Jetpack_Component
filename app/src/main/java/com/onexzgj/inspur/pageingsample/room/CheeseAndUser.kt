package com.onexzgj.inspur.pageingsample.room

import androidx.room.Embedded
import androidx.room.Relation

/**
 * des：
 * author：onexzgj
 * time：2020/3/27
 */
data class CheeseAndUser(
    @Embedded val owner: Cheese,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val user: User
)