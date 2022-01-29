package ru.s44khin.crypto.data.network

import retrofit2.Response
import ru.s44khin.crypto.data.model.BaseResponse

interface CoinRepository {

    suspend fun getListOfCoins(): Response<BaseResponse>
}