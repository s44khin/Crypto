package ru.s44khin.crypto.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.s44khin.crypto.data.database.CryptoDatabase
import ru.s44khin.crypto.data.database.CryptoDatabaseImpl
import ru.s44khin.crypto.data.database.DatabaseRoom

@Module
object DatabaseModule {

    @Provides
    fun provideDatabaseRoom(context: Context) = Room.databaseBuilder(
        context,
        DatabaseRoom::class.java,
        "databse"
    ).build()

    @Provides
    fun provideDatabase(databaseRoom: DatabaseRoom): CryptoDatabase = CryptoDatabaseImpl(databaseRoom)
}