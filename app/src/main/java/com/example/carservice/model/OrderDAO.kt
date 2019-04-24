package com.example.carservice.model

import android.arch.persistence.room.*

@Dao
interface OrderDAO {
    @Query("SELECT * FROM `Order`")
    fun getAll(): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGender(order: Order)

    @Update
    fun updateGender(order: Order)

    @Delete
    fun deleteGender(order: Order)
}