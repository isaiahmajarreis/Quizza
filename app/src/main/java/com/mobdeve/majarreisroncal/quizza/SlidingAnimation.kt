package com.mobdeve.majarreisroncal.quizza

import android.content.res.Resources
import android.widget.ImageView

/**
 * Class that allows icons to seemingly move diagonally across the screen
 */
class SlidingAnimation(private val image: ImageView) {
    private val width = Resources.getSystem().displayMetrics.widthPixels
    private val height = Resources.getSystem().displayMetrics.heightPixels

    private var speed = (5..10).random().toFloat()

    /**
     * Randomizes the spawning position of the icon to anywhere inside the screen
     */
    fun initializePosition() {
        image.x = (-90..width).random().toFloat()
        image.y = (-90..height).random().toFloat()
    }

    /**
     * "Moves" the icon leftwards and downwards. If it goes beyond the screen, it pops back out
     * from the other side and when it does, its speed is randomized
     */
    fun move() {
        image.x += speed
        image.y += speed

        if(image.x > width || image.y > height) {
            val rand = (0..1).random()
            speed = (5..10).random().toFloat()

            if(rand == 0) {
                image.x = (-90..width).random().toFloat()
                image.y = -90f
            }
            else {
                image.x = -90f
                image.y = (-90..height).random().toFloat()
            }
        }
    }
}