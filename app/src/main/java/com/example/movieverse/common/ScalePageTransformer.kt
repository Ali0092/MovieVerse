package com.example.movieverse.common

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ScalePageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {

        val scaleFactor = 0.85f + (1 - abs(position)) * 0.20f
        page.scaleX = scaleFactor
        page.scaleY = scaleFactor

        // Adjust transparency
        val alphaFactor = 0.5f + (1 - abs(position)) * 0.5f
        page.alpha = alphaFactor

        // Move non-selected items slightly behind
        val translationZFactor = -abs(position) * 10f // Adjust stacking depth
        page.translationZ = translationZFactor

        // Move non-selected items slightly closer to center (horizontally)
        val translationXFactor = position * -100f // Adjust overlap distance
        page.translationX = translationXFactor


    }
}
