package ru.s44khin.crypto.data.network

class CoinRepositoryImpl(
    private val service: CoinService
) : CoinRepository {

    override suspend fun getListOfCoins() = service.getListOfCoins()

    override suspend fun getCoin(id: String) = service.getCoin(id)
}