package com.example.carservice.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Price") val price: Double
)