package ru.s44khin.crypto.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialContainerTransform
import ru.s44khin.crypto.R

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }
}