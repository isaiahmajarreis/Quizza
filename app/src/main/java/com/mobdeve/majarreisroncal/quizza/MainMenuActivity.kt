package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainMenuBinding
import com.mobdeve.majarreisroncal.quizza.opentriviaAPI.OpenTrivia
import java.util.*
import java.util.concurrent.Executors
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
//    private val loadTokenExecutor = Executors.newSingleThreadExecutor()
//    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
//        loadToken()

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

//            val play = PlayActivity::class.java
//            val goToPlay = Intent(this, play)
//                .apply { putExtra(EXTRA_MESSAGE_TOKEN, token)}
//            startActivity(goToPlay)
            // Timer: 180 Seconds
//            timer(initialDelay = 1000L, period = 18000L)

//            val test = TestActivity::class.java
//            val goToTest = Intent(this, test)
//            startActivity(goToTest)

            val countdown = CountdownActivity::class.java
            val goToCountdown = Intent(this, countdown)

            startActivity(goToCountdown)
        }

        binding.btnStats.setOnClickListener {
            // TODO: modal for stats
            clickMp.start()
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

//    private fun loadToken() {
//        loadTokenExecutor.execute {
//            try {
//                token = OpenTrivia().getToken()
//            } catch (e: Exception) {
//                runOnUiThread {
//                    Toast.makeText(this, "An error occurred please retry later: ${e.message}", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }
}