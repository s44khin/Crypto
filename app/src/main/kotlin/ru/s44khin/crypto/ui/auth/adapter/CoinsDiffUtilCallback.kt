package ru.s44khin.crypto.ui.auth.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.s44khin.crypto.data.model.Coin

class CoinsDiffUtilCallback(
    private val oldList: List<Coin>,
    private val newList: List<Coin>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}