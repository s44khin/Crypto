package ru.s44khin.crypto.data.network

import retrofit2.Response
import ru.s44khin.crypto.data.model.BaseCoinResponse
import ru.s44khin.crypto.data.model.BaseCoinsResponse

interface CoinRepository {

    suspend fun getListOfCoins(): Response<BaseCoinsResponse>

    suspend fun getCoin(id: String): Response<BaseCoinResponse>
}