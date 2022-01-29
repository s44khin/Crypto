package ru.s44khin.crypto.ui.auth1

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.s44khin.crypto.R
import ru.s44khin.crypto.databinding.FragmentAuth1Binding
import ru.s44khin.crypto.ui.AuthFragment2

class AuthFragment1 : Fragment(R.layout.fragment_auth1) {

    private val binding by viewBinding(FragmentAuth1Binding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {
            parentFragmentManager.commit {
                addSharedElement(binding.next, binding.next.transitionName)
                replace(R.id.rootContainer, AuthFragment2())
            }
        }
        exitTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.fade)
    }
}