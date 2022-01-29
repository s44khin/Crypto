package ru.s44khin.crypto.utils

import androidx.lifecycle.MutableLiveData

internal fun <T> mutableLiveDataOf() = MutableLiveData<T>()

internal fun <T> mutableLiveDataOf(arg: T) = MutableLiveData(arg)