package ru.s44khin.crypto.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import ru.s44khin.crypto.R
import ru.s44khin.crypto.appComponent
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.databinding.FragmentMainBinding
import ru.s44khin.crypto.ui.main.adapters.PagerAdapter
import ru.s44khin.crypto.ui.tab.TabFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {

        fun newInstance() = MainFragment()
    }

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.Factory(
            database = requireContext().appComponent.database
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.usesCoins.observe(viewLifecycleOwner) { coins ->
            initTabs(coins)
        }
    }

    private fun initTabs(coins: List<Coin>) {
        val listOfFragments = mutableListOf<Fragment>()

        for (i in coins.indices)
            listOfFragments.add(
                TabFragment.newInstance(
                    id = coins[i].id,
                    symbol = coins[i].symbol,
                    name = coins[i].name
                )
            )

        binding.viewPager.adapter = PagerAdapter(
            listOfFragments,
            childFragmentManager,
            lifecycle
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = coins[position].symbol
        }.attach()
    }
}