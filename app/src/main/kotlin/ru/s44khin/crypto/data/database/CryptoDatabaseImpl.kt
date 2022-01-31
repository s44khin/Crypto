package ru.s44khin.crypto.data.database

import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.data.model.Currency

class CryptoDatabaseImpl(
    private val databaseRoom: DatabaseRoom
) : CryptoDatabase {

    override suspend fun getAllCoins() = databaseRoom.coinDao().getAll()

    override suspend fun insertAllCoins(coins: List<Coin>) = databaseRoom.coinDao().insertAll(coins)

    override suspend fun deleteCoin(coin: Coin) = databaseRoom.coinDao().delete(coin)

    override suspend fun getAllCurrencies(): List<Currency> = databaseRoom.currencyDao().getAll()

    override suspend fun instertAllCurrencies(currencies: List<Currency>) =
        databaseRoom.currencyDao().insetsAll(currencies)

    override suspend fun deleteCurrency(currency: Currency) =
        databaseRoom.currencyDao().delete(currency)
}