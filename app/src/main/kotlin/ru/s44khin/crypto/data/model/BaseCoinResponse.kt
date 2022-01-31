package ru.s44khin.crypto.data.model

import com.squareup.moshi.Json

data class BaseCoinResponse(

    @field:Json(name = "data")
    val coin: Coin
)