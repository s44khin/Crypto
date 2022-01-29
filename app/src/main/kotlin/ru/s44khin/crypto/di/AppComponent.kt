package ru.s44khin.crypto.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.s44khin.crypto.data.database.Database
import ru.s44khin.crypto.data.network.CoinRepository

@Component(modules = [AppModule::class])
interface AppComponent {

    val repository: CoinRepository

    val database: Database

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}