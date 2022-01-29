package ru.s44khin.crypto.ui.auth1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.databinding.ItemCoinBinding

class CoinsAdapter(
    private val coins: List<Coin> = emptyList(),
    private val itemClickHandler: ItemClickHandler
) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        val checkBox: CheckBox = binding.checkBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.text = coins[position].name
        holder.checkBox.setOnClickListener {
            if (holder.checkBox.isChecked)
                itemClickHandler.onCheck(coins[position])
            else
                itemClickHandler.onUnCheck(coins[position])
        }
    }

    override fun getItemCount() = coins.size
}