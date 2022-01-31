package ru.s44khin.crypto.ui.auth2.adapter

import ru.s44khin.crypto.data.model.Currency

interface ItemClickHandler {

    fun onCheck(currency: Currency)

    fun onUnCheck(currency: Currency)
}