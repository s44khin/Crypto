package ru.s44khin.crypto.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Coin")
data class Coin(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    @field:Json(name = "name")
    val name: String,

    @ColumnInfo(name = "symbol")
    @field:Json(name = "symbol")
    val symbol: String,

    @ColumnInfo(name = "rank")
    @field:Json(name = "rank")
    val rank: Int,

    @ColumnInfo(name = "priceUsd")
    @field:Json(name = "priceUsd")
    val priceUsd: Double
)