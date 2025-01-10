package com.example.movieverse.common

import android.view.View
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.movieverse.R
import kotlin.math.abs

class SliderTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {
        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.46f
        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f
    }

    override fun transformPage(page: View, position: Float) {

        page.apply {
            ViewCompat.setElevation(page, -abs(position))
            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
            when (position)  {
                in 0f..offscreenPageLimit - 1f -> {
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                }
                else -> {
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                }
            }

//            val recommendedMovieIV: ImageFilterView = findViewById(R.id.ivMoviePoster)
//            if (position <= -1.0f || position >= 1.0f) {
//                recommendedMovieIV.alpha = 0.5f
//            } else if (position == 0.5f) {
//                recommendedMovieIV.alpha = 1.0f
//            } else if (position < 0.5f) {
//                recommendedMovieIV.alpha = 1.0f - abs(position)
//            }

        }
    }
}