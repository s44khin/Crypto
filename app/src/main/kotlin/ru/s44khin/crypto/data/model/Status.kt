package ru.s44khin.crypto.data.model

import com.squareup.moshi.Json

data class Status(

    @field:Json(name = "timestamp")
    val timestamp: String,

    @field:Json(name = "errorCode")
    val errorCode: Int,

    @field:Json(name = "errorMessage")
    val errorMessage: String?,

    @field:Json(name = "elapsed")
    val elapsed: Int
)
