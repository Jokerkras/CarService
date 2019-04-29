package com.example.carservice.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["order_id", "task_id"])
data class OrderWithTask (
    @ColumnInfo(name = "order_id") val order_id: Long,
    @ColumnInfo(name = "task_id") val task_id: Long
)