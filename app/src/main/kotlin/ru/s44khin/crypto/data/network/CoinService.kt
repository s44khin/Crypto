package ru.s44khin.crypto.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.s44khin.crypto.data.model.BaseCoinResponse
import ru.s44khin.crypto.data.model.BaseCoinsResponse

interface CoinService {

    @GET("assets")
    suspend fun getListOfCoins(): Response<BaseCoinsResponse>

    @GET("assets/{id}")
    suspend fun getCoin(
        @Path("id") id: String
    ): Response<BaseCoinResponse>
}