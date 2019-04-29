package com.example.carservice.model

import android.arch.persistence.room.*

@Dao
interface OrderWithTaskDAO {

    @Insert
    fun insert(orderWithTask: OrderWithTask)

    @Transaction @Query("SELECT * FROM OrderWithTask WHERE order_id = :id")
    fun getOrderWithTasks(id: Long): List<OrderWithTask>
}