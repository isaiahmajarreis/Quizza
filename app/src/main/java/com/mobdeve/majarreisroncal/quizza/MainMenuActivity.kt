package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainMenuBinding
import java.util.*
import kotlin.system.exitProcess

/**
 * The main menu where most of the functions can be accessed
 */
class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    private lateinit var mushroom: SlidingAnimation
    private lateinit var tomato: SlidingAnimation
    private lateinit var bellPepper: SlidingAnimation
    private lateinit var onion: SlidingAnimation
    private lateinit var garlic: SlidingAnimation
    private lateinit var cheese: SlidingAnimation
    private lateinit var pepperoni: SlidingAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val statsFragment = StatsFragment()
        val clickMp = MediaPlayer.create(this, R.raw.buttonclick)

        animationIcons()

        /**
         * Moves to the game screen
         */
        binding.btnPlay.setOnClickListener {
            clickMp.start()

            val countdown = CountdownActivity::class.java
            val goToCountdown = Intent(this, countdown)
            startActivity(goToCountdown)
        }

        /**
         * Displays player statistics in the form of a modal
         */
        binding.btnStats.setOnClickListener {
            clickMp.start()
            statsFragment.show(supportFragmentManager,"BottomSheetDialog")
        }

        /**
         * Display the available options
         */
        binding.btnOptions.setOnClickListener {
            clickMp.start()

            val goToOptions = Intent(this, OptionsActivity::class.java)
            startActivity(goToOptions)
        }

        /**
         * Displays the directions for the game
         */
        binding.btnHelp.setOnClickListener {
            clickMp.start()

            val goToHelp = Intent(this, HelpActivity::class.java)
            startActivity(goToHelp)
        }

        /**
         * Exits the game back to the phone's home screen
         */
        binding.btnExit.setOnClickListener {
            clickMp.start()

            moveTaskToBack(true)
            exitProcess(-1)
        }
    }

    /**
     * Initializes and loops the animation without starving other processes
     */
    private fun animationIcons() {
        mushroom = SlidingAnimation(binding.ivMushroom)
        tomato = SlidingAnimation(binding.ivTomato)
        bellPepper = SlidingAnimation(binding.ivBellPepper)
        onion = SlidingAnimation(binding.ivOnion)
        garlic = SlidingAnimation(binding.ivGarlic)
        cheese = SlidingAnimation(binding.ivCheese)
        pepperoni = SlidingAnimation(binding.ivPepperoni)

        mushroom.initializePosition()
        tomato.initializePosition()
        bellPepper.initializePosition()
        onion.initializePosition()
        garlic.initializePosition()
        cheese.initializePosition()
        pepperoni.initializePosition()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    mushroom.move()
                    tomato.move()
                    bellPepper.move()
                    onion.move()
                    garlic.move()
                    cheese.move()
                    pepperoni.move()
                }
            }
        }, 0, 20)
    }
}