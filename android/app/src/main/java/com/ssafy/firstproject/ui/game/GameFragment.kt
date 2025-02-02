package com.ssafy.firstproject.ui.game

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.firstproject.R
import com.ssafy.firstproject.base.BaseFragment
import com.ssafy.firstproject.databinding.FragmentGameBinding

private const val TAG = "GameFragment"

class GameFragment : BaseFragment<FragmentGameBinding>(
    FragmentGameBinding::bind,
    R.layout.fragment_game
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.dest_choice_dialog)
        }
    }
}