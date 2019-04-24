package com.example.carservice.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val price: Double
)