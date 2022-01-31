package ru.s44khin.crypto.ui.auth2

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.transition.Hold
import ru.s44khin.crypto.R
import ru.s44khin.crypto.data.model.Currency
import ru.s44khin.crypto.databinding.FragmentAuth2Binding
import ru.s44khin.crypto.ui.MainActivity
import ru.s44khin.crypto.ui.auth2.adapter.CurrenciesAdapter
import ru.s44khin.crypto.ui.auth2.adapter.ItemClickHandler
import ru.s44khin.crypto.ui.main.MainFragment

class Auth2Fragment : Fragment(R.layout.fragment_auth2), ItemClickHandler {

    private val binding by viewBinding(FragmentAuth2Binding::bind)

    private val viewModel: Auth2ViewModel by viewModels()

    private val sharedPreferences by lazy {
        requireActivity().getSharedPreferences(MainActivity.SETTINGS, Context.MODE_PRIVATE)
    }

    private var currencies = ArrayList<Currency>()

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

        binding.next.setOnClickListener {
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
            adapter = CurrenciesAdapter(
                currencies = currencies,
                itemClickHandler = this@Auth2Fragment
            )
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