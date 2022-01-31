package ru.s44khin.crypto.ui.tab

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.s44khin.crypto.R
import ru.s44khin.crypto.appComponent
import ru.s44khin.crypto.databinding.FragmentTabBinding
import kotlin.math.roundToInt

class TabFragment : Fragment(R.layout.fragment_tab) {

    companion object {

        private const val ID = "ID"
        private const val SYMBOL = "SYMBOL"
        private const val NAME = "NAME"

        fun newInstance(id: String, symbol: String, name: String): TabFragment {
            val bundle = bundleOf(ID to id, SYMBOL to symbol, NAME to name)
            val fragment = TabFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: TabViewModel by viewModels {
        TabViewModel.Factory(
            id = arguments?.getString(ID, "bitcoin") ?: "bitcoin",
            repository = requireContext().appComponent.repository
        )
    }

    private val binding by viewBinding(FragmentTabBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.coin.observe(viewLifecycleOwner) { coin ->
            binding.text.text =
                resources.getString(
                    R.string.equals_formatted,
                    coin.symbol,
                    coin.priceUsd
                )
        }
    }
}