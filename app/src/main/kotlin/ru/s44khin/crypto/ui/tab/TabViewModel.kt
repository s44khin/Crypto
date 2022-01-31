package ru.s44khin.crypto.ui.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.data.network.CoinRepository
import ru.s44khin.crypto.utils.mutableLiveDataOf

class TabViewModel(
    id: String,
    private val repository: CoinRepository
) : ViewModel() {

    private val _coin = mutableLiveDataOf<Coin>()
    internal val coin get() = _coin as LiveData<Coin>

    init {
        getCoin(id)
    }

    private fun getCoin(id: String) = CoroutineScope(Dispatchers.IO).launch {
        val result = repository.getCoin(id).body()

        withContext(Dispatchers.Main) {
            result?.let { response ->
                _coin.value = response.coin
            }
        }
    }

    class Factory(
        private val id: String,
        private val repository: CoinRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(TabViewModel::class.java))
            return TabViewModel(id, repository) as T
        }
    }
}