package ru.s44khin.crypto.data.model

import com.squareup.moshi.Json

data class BaseCurrencies(

    @field:Json(name = "currencies")
    val currencies: List<Currency>
)