package ru.s44khin.crypto.data.database

import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.data.model.Currency

interface CryptoDatabase {

    suspend fun getAllCoins(): List<Coin>

    suspend fun insertAllCoins(coins: List<Coin>)

    suspend fun deleteCoin(coin: Coin)

    suspend fun getAllCurrencies(): List<Currency>

    suspend fun instertAllCurrencies(currencies: List<Currency>)

    suspend fun deleteCurrency(currency: Currency)
}