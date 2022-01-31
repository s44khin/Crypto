package ru.s44khin.crypto.ui.auth2

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.transition.Hold
import ru.s44khin.crypto.R
import ru.s44khin.crypto.appComponent
import ru.s44khin.crypto.data.model.Currency
import ru.s44khin.crypto.databinding.FragmentAuth2Binding
import ru.s44khin.crypto.ui.MainActivity
import ru.s44khin.crypto.ui.auth2.adapter.CurrenciesAdapter
import ru.s44khin.crypto.ui.auth2.adapter.CurrenciesDiffutilCallback
import ru.s44khin.crypto.ui.auth2.adapter.ItemClickHandler
import ru.s44khin.crypto.ui.main.MainFragment

class Auth2Fragment : Fragment(R.layout.fragment_auth2), ItemClickHandler {

    private val binding by viewBinding(FragmentAuth2Binding::bind)

    private val viewModel: Auth2ViewModel by viewModels {
        Auth2ViewModel.Factory(
            database = requireContext().appComponent.database
        )
    }

    private val sharedPreferences by lazy {
        requireActivity().getSharedPreferences(MainActivity.SETTINGS, Context.MODE_PRIVATE)
    }

    private var currencies = ArrayList<Currency>()

    private val adapter by lazy {
        CurrenciesAdapter(
            itemClickHandler = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCurrencies(requireContext())

        enterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.slide_right)
        exitTransition = Hold()
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.hide()
        initObservable()
        initSearch()

        binding.next.setOnClickListener {
            viewModel.insertUsesCurrencies(currencies)
            parentFragmentManager.commit {
                sharedPreferences.edit().putBoolean(MainActivity.START, true).apply()
                addSharedElement(binding.next, binding.next.transitionName)
                replace(R.id.rootContainer, MainFragment())
            }
        }
    }

    private fun initObservable() = viewModel.currencies.observe(viewLifecycleOwner) { currencies ->
        binding.listOfCurrency.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this@Auth2Fragment.adapter.currencies = currencies
            adapter = this@Auth2Fragment.adapter
        }
    }

    private fun initSearch() = binding.search.doAfterTextChanged { text ->
        val request = text.toString()

        viewModel.currencies.value?.let { currencies ->
            val newList = currencies.filter { currency -> currency.code.contains(request, true) }
            val layoutManagerState = binding.listOfCurrency.layoutManager?.onSaveInstanceState()
            val callback = CurrenciesDiffutilCallback(adapter.currencies, newList)
            val diffUtilResult = DiffUtil.calculateDiff(callback, true)
            adapter.currencies = newList
            diffUtilResult.dispatchUpdatesTo(adapter)
            binding.listOfCurrency.layoutManager?.onRestoreInstanceState(layoutManagerState)
        }
    }

    override fun onCheck(currency: Currency) {
        currencies += currency

        if (currencies.size != 0)
            binding.next.show()
    }

    override fun onUnCheck(currency: Currency) {
        currencies -= currency

        if (currencies.size == 0)
            binding.next.hide()
    }
}