package ru.s44khin.crypto.ui.auth1

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.s44khin.crypto.R
import ru.s44khin.crypto.appComponent
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.databinding.FragmentAuth1Binding
import ru.s44khin.crypto.ui.auth1.adapter.CoinsAdapter
import ru.s44khin.crypto.ui.auth1.adapter.CoinsDiffUtilCallback
import ru.s44khin.crypto.ui.auth1.adapter.ItemClickHandler
import ru.s44khin.crypto.ui.auth2.Auth2Fragment

class Auth1Fragment : Fragment(R.layout.fragment_auth1), ItemClickHandler {

    companion object {

        fun newInstance() = Auth1Fragment()
    }

    private val binding by viewBinding(FragmentAuth1Binding::bind)

    private val viewModel: Auth1ViewModel by viewModels {
        Auth1ViewModel.Factory(
            repository = requireContext().appComponent.repository,
            database = requireContext().appComponent.database
        )
    }

    private var coins = ArrayList<Coin>()

    private val adapter by lazy {
        CoinsAdapter(
            itemClickHandler = this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.hide()

        binding.next.setOnClickListener {
            viewModel.insertUsesCoins(coins)
            parentFragmentManager.commit {
                addSharedElement(binding.next, binding.next.transitionName)
                replace(R.id.rootContainer, Auth2Fragment.newInstance())
            }
        }

        exitTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.fade)

        initObserver()
        initSearch()
    }

    private fun initObserver() = viewModel.coins.observe(viewLifecycleOwner) { coins ->
        binding.apply {
            listOfCoins.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter.coins = coins
            listOfCoins.adapter = adapter
            shimmer.isVisible = false
        }
    }

    private fun initSearch() = binding.search.doAfterTextChanged { text ->
        val request = text.toString()

        viewModel.coins.value?.let { coins ->
            val newList = coins.filter { coin -> coin.name.contains(request, true) }
            val layoutManagerState = binding.listOfCoins.layoutManager?.onSaveInstanceState()
            val callback = CoinsDiffUtilCallback(adapter.coins, newList)
            val diffUtilResult = DiffUtil.calculateDiff(callback, true)
            adapter.coins = newList
            diffUtilResult.dispatchUpdatesTo(adapter)
            binding.listOfCoins.layoutManager?.onRestoreInstanceState(layoutManagerState)
        }
    }

    override fun onCheck(coin: Coin) {
        coins += coin

        if (coins.size != 0)
            binding.next.show()
    }

    override fun onUnCheck(coin: Coin) {
        coins -= coin

        if (coins.size == 0)
            binding.next.hide()
    }
}