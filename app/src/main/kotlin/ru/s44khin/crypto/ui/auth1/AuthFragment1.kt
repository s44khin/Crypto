package ru.s44khin.crypto.ui.auth1

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.s44khin.crypto.R
import ru.s44khin.crypto.appComponent
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.databinding.FragmentAuth1Binding
import ru.s44khin.crypto.ui.MainViewModel
import ru.s44khin.crypto.ui.auth1.adapter.CoinsAdapter
import ru.s44khin.crypto.ui.auth1.adapter.ItemClickHandler
import ru.s44khin.crypto.ui.auth2.AuthFragment2

class AuthFragment1 : Fragment(R.layout.fragment_auth1), ItemClickHandler {

    private val binding by viewBinding(FragmentAuth1Binding::bind)

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.Factory(
            requireContext().appComponent.repository,
            requireContext().appComponent.database
        )
    }

    private var coins = ArrayList<Coin>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.hide()

        binding.next.setOnClickListener {
            viewModel.insertCoins(coins)
            parentFragmentManager.commit {
                addSharedElement(binding.next, binding.next.transitionName)
                replace(R.id.rootContainer, AuthFragment2())
            }
        }

        exitTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.fade)

        initObserver()
    }

    private fun initObserver() = viewModel.listOfCoins.observe(viewLifecycleOwner) { coins ->
        binding.apply {
            listOfCoins.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            listOfCoins.adapter = CoinsAdapter(
                coins = coins,
                itemClickHandler = this@AuthFragment1
            )

            shimmer.isVisible = false
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