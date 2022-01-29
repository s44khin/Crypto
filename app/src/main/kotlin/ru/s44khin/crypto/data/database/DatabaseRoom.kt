package ru.s44khin.crypto.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.s44khin.crypto.data.model.Coin

@Database(
    entities = [Coin::class],
    version = 1
)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun coinDao(): CoinDao
}