package ru.s44khin.crypto.ui.auth2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.s44khin.crypto.R
import ru.s44khin.crypto.data.database.CryptoDatabase
import ru.s44khin.crypto.data.model.BaseCurrencies
import ru.s44khin.crypto.data.model.Currency
import ru.s44khin.crypto.utils.mutableLiveDataOf
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

class Auth2ViewModel(
    private val database: CryptoDatabase
) : ViewModel() {

    private val _currencies = mutableLiveDataOf<List<Currency>>()
    internal val currencies get() = _currencies as LiveData<List<Currency>>

    fun getCurrencies(context: Context) {
        val inputStream = context.resources.openRawResource(R.raw.currencies)
        val writer = StringWriter()
        val buffer = CharArray(1024)

        inputStream.use {
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n = reader.read(buffer)

            while (n != -1) {
                writer.write(buffer, 0, n)
                n = reader.read(buffer)
            }
        }

        inputStream.close()

        _currencies.value = writer.toString().toListOfCurrencies()
    }

    fun insertUsesCurrencies(currencies: List<Currency>) = CoroutineScope(Dispatchers.IO).launch {
        database.instertAllCurrencies(currencies)
    }

    private fun String.toListOfCurrencies(): List<Currency> {
        val jsonAdapter: JsonAdapter<BaseCurrencies> =
            Moshi.Builder().build().adapter(BaseCurrencies::class.java)
        return jsonAdapter.fromJson(this)?.currencies ?: emptyList()
    }

    class Factory(
        private val database: CryptoDatabase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(Auth2ViewModel::class.java))
            return Auth2ViewModel(database) as T
        }
    }
}