package com.ssafy.firstproject.ui.gameresult

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.ssafy.firstproject.R
import com.ssafy.firstproject.base.BaseFragment
import com.ssafy.firstproject.databinding.FragmentGameResultBinding


private const val TAG = "GameResultFragment"

class GameResultFragment : BaseFragment<FragmentGameResultBinding>(
    FragmentGameResultBinding::bind,
    R.layout.fragment_game_result
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPieChart()
    }

    private fun setPieChart() {
        binding.pieChart.setUsePercentValues(true)

        // 데이터 세팅
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(15f, getString(R.string.real)))
        entries.add(PieEntry(10f, getString(R.string.fake_news)))
        entries.add(PieEntry(3f, getString(R.string.exaggerated_news)))
        entries.add(PieEntry(72f, getString(R.string.clickbait)))


        // 색상 세팅
        val colorsItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
//        for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
//        for (c in COLORFUL_COLORS) colorsItems.add(c)
//        for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
//        for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
//        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            colors = colorsItems
            valueTextColor = Color.BLACK
            valueTextSize = 18f
        }

        val pieData = PieData(pieDataSet)
        binding.pieChart.apply {
            data = pieData
            description.isEnabled = false
            isRotationEnabled = false
            centerText = getString(R.string.answer_chart)
            setEntryLabelColor(Color.BLACK)
            setCenterTextSize(20f)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }
}