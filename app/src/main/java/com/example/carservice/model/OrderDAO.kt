package com.example.carservice.model

import android.arch.persistence.room.*

@Dao
interface OrderDAO {
    @Query("SELECT * FROM `Order`")
    fun getAll(): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: Order)

    @Update
    fun updateOrder(order: Order)

    @Delete
    fun deleteOrder(order: Order)
}