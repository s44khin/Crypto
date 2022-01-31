package ru.s44khin.crypto.data.database

import ru.s44khin.crypto.data.model.Coin

class CoinDatabaseImpl(
    private val databaseRoom: DatabaseRoom
) : CoinDatabase {

    override suspend fun getAll() = databaseRoom.coinDao().getAll()

    override suspend fun insertAll(coins: List<Coin>) = databaseRoom.coinDao().insertAll(coins)

    override suspend fun delete(coin: Coin) = databaseRoom.coinDao().delete(coin)
}