package com.example.carservice.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TaskDAO {
    @Insert
    fun insertTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTask(id: Long): Task

    @Query("SELECT SUM(Price) FROM Task WHERE id IN (:ids)")
    fun getPrice(ids: List<Long>): Double
}