package ru.s44khin.crypto.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Currency")
data class Currency(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    val id: Int,

    @ColumnInfo(name = "code")
    @field:Json(name = "code")
    val code: String
)
