package com.ssafy.firstproject.ui.check

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.ssafy.firstproject.R
import com.ssafy.firstproject.base.BaseFragment
import com.ssafy.firstproject.databinding.FragmentCheckBinding

private const val TAG = "CheckFragment_ssafy"
class CheckFragment : BaseFragment<FragmentCheckBinding>(
    FragmentCheckBinding::bind,
    R.layout.fragment_check
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val typeList = resources.getStringArray(R.array.check_types)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            typeList
        )

        binding.autoCompleteTextView.setAdapter(adapter)
    }
}