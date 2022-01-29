package ru.s44khin.crypto.data.database

import androidx.room.*
import ru.s44khin.crypto.data.model.Coin

@Dao
interface CoinDao {

    @Query("SELECT * FROM Coin")
    suspend fun getAll(): List<Coin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coins: List<Coin>)

    @Delete
    suspend fun delete(coin: Coin)
}