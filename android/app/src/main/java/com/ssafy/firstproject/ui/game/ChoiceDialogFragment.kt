package com.ssafy.firstproject.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.firstproject.R
import com.ssafy.firstproject.databinding.DialogChoiceBinding

class ChoiceDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogChoiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReal.setOnClickListener {
            findNavController().navigate(R.id.dest_game_result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}