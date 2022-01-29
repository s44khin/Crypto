package ru.s44khin.crypto.data.database

import ru.s44khin.crypto.data.model.Coin

class DatabaseImpl(
    private val databaseRoom: DatabaseRoom
) : Database {

    override suspend fun getAll() = databaseRoom.coinDao().getAll()

    override suspend fun insertAll(coins: List<Coin>) = databaseRoom.coinDao().insertAll(coins)

    override suspend fun delete(coin: Coin) = databaseRoom.coinDao().delete(coin)
}