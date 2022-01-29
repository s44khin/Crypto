package ru.s44khin.crypto.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.s44khin.crypto.R
import ru.s44khin.crypto.databinding.ActivityMainBinding
import ru.s44khin.crypto.ui.auth1.AuthFragment1
import ru.s44khin.crypto.ui.main.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val SETTINGS = "SETTINGS"
        const val START = "START"
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val sharedPreferences by lazy {
        getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                if (sharedPreferences.contains(START))
                    add(binding.rootContainer.id, MainFragment())
                else
                    add(binding.rootContainer.id, AuthFragment1())
            }
        }
    }
}