package ru.s44khin.crypto.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import ru.s44khin.crypto.R
import ru.s44khin.crypto.appComponent
import ru.s44khin.crypto.data.model.Coin
import ru.s44khin.crypto.databinding.FragmentMainBinding
import ru.s44khin.crypto.databinding.FragmentTabBinding
import ru.s44khin.crypto.ui.main.adapters.PagerAdapter

class MainFragment : Fragment(R.layout.fragment_main) {

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
        val tabs: List<String> = coins.map { it.name }
        val listOfFragments = mutableListOf<Fragment>()

        for (i in tabs.indices)
            listOfFragments.add(MyFragment.newInstance(tabs[i]))

        binding.viewPager.adapter = PagerAdapter(
            listOfFragments,
            childFragmentManager,
            lifecycle
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }
}

class MyFragment : Fragment(R.layout.fragment_tab) {

    companion object {
        const val TAG = "tag"

        fun newInstance(text: String): MyFragment {
            val bundle = bundleOf(TAG to text)
            val fragment = MyFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val binding by viewBinding(FragmentTabBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text.text = requireArguments().getString(TAG)
    }
}