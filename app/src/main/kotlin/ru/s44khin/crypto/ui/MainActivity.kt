package ru.s44khin.crypto.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.s44khin.crypto.R
import ru.s44khin.crypto.databinding.ActivityMainBinding
import ru.s44khin.crypto.ui.auth1.AuthFragment1

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val SETTINGS = "SETTINGS"
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        supportFragmentManager.commit {
            add(binding.rootContainer.id, AuthFragment1())
        }
    }
}