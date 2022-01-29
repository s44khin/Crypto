package ru.s44khin.crypto.data.model

import com.squareup.moshi.Json

data class BaseResponse(

    @field:Json(name = "status")
    val status: Status,

    @field:Json(name = "data")
    val coins: List<Coin>
)