package ru.s44khin.crypto.ui.auth2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import ru.s44khin.crypto.data.model.Currency
import ru.s44khin.crypto.databinding.ItemCoinBinding

class CurrenciesAdapter(
    private val currencies: List<Currency> = emptyList(),
    private val itemClickHandler: ItemClickHandler
) : RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    private val checkedCoins = arrayListOf<Int>()

    class ViewHolder(binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        val checkBox: CheckBox = binding.checkBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = currencies[position]

        holder.checkBox.text = currency.code

        if (currency.id in checkedCoins)
            holder.checkBox.isChecked = true

        holder.checkBox.setOnClickListener {
            if (holder.checkBox.isChecked) {
                itemClickHandler.onCheck(currency)
                checkedCoins += currency.id
            } else {
                itemClickHandler.onUnCheck(currency)
                checkedCoins -= currency.id
            }
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.checkBox.isChecked = false
    }

    override fun getItemCount() = currencies.size
}