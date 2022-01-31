package ru.s44khin.crypto.data.database

import ru.s44khin.crypto.data.model.Coin

interface CoinDatabase {

    suspend fun getAll(): List<Coin>

    suspend fun insertAll(coins: List<Coin>)

    suspend fun delete(coin: Coin)
}