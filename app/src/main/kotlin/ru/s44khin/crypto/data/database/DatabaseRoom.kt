package ru.s44khin.crypto.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.data.model.Currency

@Database(
    entities = [Coin::class, Currency::class],
    version = 1
)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    abstract fun currencyDao(): CurrencyDao
}