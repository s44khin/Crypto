package ru.s44khin.crypto.ui.auth.adapter

import ru.s44khin.crypto.data.model.Coin

interface ItemClickHandler {

    fun onCheck(coin: Coin)

    fun onUnCheck(coin: Coin)
}