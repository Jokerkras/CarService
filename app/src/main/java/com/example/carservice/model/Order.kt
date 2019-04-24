package com.example.carservice.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Order (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "VIN") val VIN: String,
    @ColumnInfo(name = "Mark") val Mark: String,
    @ColumnInfo(name = "Model") val Model: String,
    @ColumnInfo(name = "Number") val Number: String,
    @ColumnInfo(name = "Date") val Date: Date,
    @ColumnInfo(name = "Ready") val Ready: Boolean
)