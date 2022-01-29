package ru.s44khin.crypto.data.network

import retrofit2.Response
import retrofit2.http.GET
import ru.s44khin.crypto.data.model.BaseResponse

interface CoinService {

    @GET("v1/cryptocurrency/map?limit=100&sort=cmc_rank")
    suspend fun getListOfCoins(): Response<BaseResponse>
}