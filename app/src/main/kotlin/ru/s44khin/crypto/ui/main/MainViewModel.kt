package ru.s44khin.crypto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.s44khin.crypto.data.database.CoinDatabase
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.utils.mutableLiveDataOf

class MainViewModel(
    private val database: CoinDatabase
) : ViewModel() {

    private val _usesCoins = mutableLiveDataOf<List<Coin>>()
    internal val usesCoins get() = _usesCoins as LiveData<List<Coin>>

    init {
        getUsesCoins()
    }

    private fun getUsesCoins() = CoroutineScope(Dispatchers.IO).launch {
        val result = database.getAll()

        withContext(Dispatchers.Main) {
            _usesCoins.value = result
        }
    }

    class Factory(
        private val database: CoinDatabase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(database) as T
        }
    }
}