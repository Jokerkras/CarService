package com.example.carservice.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Order (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val VIN: String,
    val Mark: String,
    val Model: String,
    val Number: String,
    val Date: Date
)