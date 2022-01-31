package ru.s44khin.crypto.ui.auth1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.databinding.ItemCoinBinding

class CoinsAdapter(
    internal var coins: List<Coin> = emptyList(),
    private val itemClickHandler: ItemClickHandler
) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    private val checkedCoins = arrayListOf<Int>()

    class ViewHolder(binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        val checkBox: CheckBox = binding.checkBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = coins[position]

        holder.checkBox.text = coin.name

        if (coin.id in checkedCoins)
            holder.checkBox.isChecked = true

        holder.checkBox.setOnClickListener {
            if (holder.checkBox.isChecked) {
                itemClickHandler.onCheck(coin)
                checkedCoins += coin.id
            } else {
                itemClickHandler.onUnCheck(coin)
                checkedCoins -= coin.id
            }
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.checkBox.isChecked = false
    }

    override fun getItemCount() = coins.size
}