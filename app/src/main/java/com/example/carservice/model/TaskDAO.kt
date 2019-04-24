package com.example.carservice.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert

@Dao
interface TaskDAO {
    @Insert
    fun insertTask(task: Task)
}