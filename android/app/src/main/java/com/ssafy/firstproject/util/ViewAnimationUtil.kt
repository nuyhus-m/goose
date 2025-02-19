package com.ssafy.firstproject.util

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.ProgressBar
import com.ssafy.firstproject.R

object ViewAnimationUtil {
    @SuppressLint("UseCompatLoadingForDrawables")
    fun animateProgress(progressBar: ProgressBar, targetProgress: Int) {
        val currentProgress = progressBar.progress

        if (targetProgress <= currentProgress) return

        if (targetProgress < 33) {
            progressBar.progressDrawable = progressBar.context.getDrawable(R.drawable.bg_progress_bar_33)
        } else if (targetProgress < 66) {
            progressBar.progressDrawable = progressBar.context.getDrawable(R.drawable.bg_progress_bar_66)
        } else {
            progressBar.progressDrawable = progressBar.context.getDrawable(R.drawable.bg_progress_bar_100)
        }
        // 애니메이션 설정
        val animator = ObjectAnimator.ofInt(progressBar, "progress", currentProgress, targetProgress).apply {
            duration = 500
            interpolator = DecelerateInterpolator()
        }

        animator.start()
    }

    fun rotateImage(imageView: ImageView, rotationAngle: Float) {
        ObjectAnimator.ofFloat(imageView, "rotation", rotationAngle).apply {
            duration = 200 // 애니메이션 지속 시간 (300ms)
            start()
        }
    }
}