package ru.s44khin.crypto.data.database

import androidx.room.*
import ru.s44khin.crypto.data.model.Currency

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM Currency")
    suspend fun getAll(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetsAll(currencies: List<Currency>)

    @Delete
    suspend fun delete(currency: Currency)
}