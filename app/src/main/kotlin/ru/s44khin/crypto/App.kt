package ru.s44khin.crypto

import android.app.Application
import android.content.Context
import com.google.android.material.color.DynamicColors
import ru.s44khin.crypto.di.AppComponent
import ru.s44khin.crypto.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

internal val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }