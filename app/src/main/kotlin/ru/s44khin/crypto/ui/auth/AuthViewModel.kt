package ru.s44khin.crypto.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.s44khin.crypto.data.database.CryptoDatabase
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.data.network.CoinRepository
import ru.s44khin.crypto.utils.mutableLiveDataOf

class AuthViewModel(
    private val repository: CoinRepository,
    private val database: CryptoDatabase
) : ViewModel() {

    private val _coins = mutableLiveDataOf<List<Coin>>()
    internal val coins get() = _coins as LiveData<List<Coin>>

    init {
        getCoins()
    }

    private fun getCoins() = CoroutineScope(Dispatchers.IO).launch {
        val result = repository.getListOfCoins().body()

        withContext(Dispatchers.Main) {
            result?.let { response ->
                _coins.value = response.coins
            }
        }
    }

    fun insertUsesCoins(coins: List<Coin>) = CoroutineScope(Dispatchers.IO).launch {
        database.insertAllCoins(coins)
    }

    class Factory(
        private val repository: CoinRepository,
        private val database: CryptoDatabase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(AuthViewModel::class.java))
            return AuthViewModel(repository, database) as T
        }
    }
}