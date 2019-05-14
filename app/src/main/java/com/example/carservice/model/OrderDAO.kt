package com.example.carservice.model

import android.arch.persistence.room.*

@Dao
interface OrderDAO {
    @Query("SELECT * FROM `Order`")
    fun getAll(): List<Order>

    @Query("SELECT * FROM `Order` WHERE id = :id")
    fun getOrder(id: Long): Order

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: Order)

    @Update
    fun updateOrder(order: Order)

    @Delete
    fun deleteOrder(order: Order)
}