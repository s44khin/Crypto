package ru.s44khin.crypto.ui.auth2

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.transition.Hold
import ru.s44khin.crypto.R
import ru.s44khin.crypto.databinding.FragmentAuth2Binding
import ru.s44khin.crypto.ui.main.MainFragment

class AuthFragment2 : Fragment(R.layout.fragment_auth2) {

    private val binding by viewBinding(FragmentAuth2Binding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.slide_right)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {
            parentFragmentManager.commit {
                addSharedElement(binding.next, binding.next.transitionName)
                replace(R.id.rootContainer, MainFragment())
            }
        }
        exitTransition = Hold()
    }
}