package com.mobdeve.majarreisroncal.quizza

import android.content.res.Resources
import android.widget.ImageView

class SlidingAnimation(private val image: ImageView) {
    private val width = Resources.getSystem().displayMetrics.widthPixels
    private val height = Resources.getSystem().displayMetrics.heightPixels

    private var speed = (5..10).random().toFloat()

    fun initializePosition() {
        image.x = (-90..width).random().toFloat()
        image.y = (-90..height).random().toFloat()
    }

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