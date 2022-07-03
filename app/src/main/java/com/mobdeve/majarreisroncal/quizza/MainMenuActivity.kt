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

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val statsFragment = StatsFragment()
        val clickMp = MediaPlayer.create(this, R.raw.buttonclick)

        val mushroom = SlidingAnimation(binding.ivMushroom)
        val tomato = SlidingAnimation(binding.ivTomato)
        val bellPepper = SlidingAnimation(binding.ivBellPepper)
        val onion = SlidingAnimation(binding.ivOnion)
        val garlic = SlidingAnimation(binding.ivGarlic)
        val cheese = SlidingAnimation(binding.ivCheese)
        val pepperoni = SlidingAnimation(binding.ivPepperoni)

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

        binding.btnPlay.setOnClickListener {
            clickMp.start()

            val countdown = CountdownActivity::class.java
            val goToCountdown = Intent(this, countdown)
            startActivity(goToCountdown)
        }

        binding.btnStats.setOnClickListener {
            clickMp.start()
            statsFragment.show(supportFragmentManager,"BottomSheetDialog")
        }

        binding.btnOptions.setOnClickListener {
            clickMp.start()

            val goToOptions = Intent(this, OptionsActivity::class.java)
            startActivity(goToOptions)
        }

        binding.btnHelp.setOnClickListener {
            clickMp.start()

            val goToHelp = Intent(this, HelpActivity::class.java)
            startActivity(goToHelp)
        }

        binding.btnExit.setOnClickListener {
            clickMp.start()

            // TODO: dialog popup alert on click ("Are you sure you want to exit the game?")
            moveTaskToBack(true)
            exitProcess(-1)
        }
    }
}