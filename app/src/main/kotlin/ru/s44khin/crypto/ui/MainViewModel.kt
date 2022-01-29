package ru.s44khin.crypto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.s44khin.crypto.data.database.Database
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.data.network.CoinRepository
import ru.s44khin.crypto.utils.mutableLiveDataOf

class MainViewModel(
    private val repository: CoinRepository,
    private val database: Database
) : ViewModel() {

    private val _listOfCoins = mutableLiveDataOf<List<Coin>>()
    internal val listOfCoins get() = _listOfCoins as LiveData<List<Coin>>

    private val _databaseCoins = mutableLiveDataOf<List<Coin>>()
    internal val databaseCoint get() = _databaseCoins as LiveData<List<Coin>>

    init {
        getListOfCoins()
    }

    private fun getListOfCoins() = CoroutineScope(Dispatchers.IO).launch {
        val result = repository.getListOfCoins().body()

        withContext(Dispatchers.Main) {
            result?.let { response ->
                _listOfCoins.value = response.coins
            }
        }
    }

    fun insertCoins(coins: List<Coin>) = CoroutineScope(Dispatchers.IO).launch {
        database.insertAll(coins)
    }

    fun getCoins() = CoroutineScope(Dispatchers.IO).launch {
        val result = database.getAll()

        withContext(Dispatchers.Main) {
            _databaseCoins.value = result
        }
    }

    class Factory(
        private val repository: CoinRepository,
        private val database: Database
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repository, database) as T
        }
    }
}