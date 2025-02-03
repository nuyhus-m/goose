package com.ssafy.firstproject.ui.check

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ssafy.firstproject.R
import com.ssafy.firstproject.base.BaseFragment
import com.ssafy.firstproject.databinding.FragmentCheckBinding

class CheckFragment : BaseFragment<FragmentCheckBinding>(
    FragmentCheckBinding::bind,
    R.layout.fragment_check
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 드롭다운 항목을 선택할 때의 이벤트 처리
        binding.autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as String

            when (selectedItem) {
                getString(R.string.type_img) -> {
                    binding.mcvImg.visibility = View.VISIBLE
                    binding.tilUrl.visibility = View.GONE
                    binding.tilContent.visibility = View.GONE
                }
                getString(R.string.type_url) -> {
                    binding.mcvImg.visibility = View.GONE
                    binding.tilUrl.visibility = View.VISIBLE
                    binding.tilContent.visibility = View.GONE
                }
                getString(R.string.type_content) -> {
                    binding.mcvImg.visibility = View.GONE
                    binding.tilUrl.visibility = View.GONE
                    binding.tilContent.visibility = View.VISIBLE
                }
            }
            // 선택된 항목을 처리하는 코드
            Toast.makeText(requireContext(), "선택된 항목: $selectedItem", Toast.LENGTH_SHORT).show()
        }
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