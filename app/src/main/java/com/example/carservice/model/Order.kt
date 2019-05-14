package com.example.carservice.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import java.util.*

@Entity
data class Order (
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "VIN") val VIN: String,
    @ColumnInfo(name = "Mark") val Mark: String,
    @ColumnInfo(name = "Model") val Model: String,
    @ColumnInfo(name = "Number") val Number: String,
    @ColumnInfo(name = "Date") val Date: Date,
    @ColumnInfo(name = "Ready") var Ready: Boolean,
    @ColumnInfo(name = "Info") val Info: String
)