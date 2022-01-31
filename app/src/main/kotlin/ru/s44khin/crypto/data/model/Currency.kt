package ru.s44khin.crypto.data.model

import com.squareup.moshi.Json

data class Currency(

    @field:Json(name = "code")
    val code: String,

    @field:Json(name = "id")
    val id: Int
)
