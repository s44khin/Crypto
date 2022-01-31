package ru.s44khin.crypto.data.model

import com.squareup.moshi.Json

data class BaseCoinsResponse(

    @field:Json(name = "data")
    val coins: List<Coin>
)