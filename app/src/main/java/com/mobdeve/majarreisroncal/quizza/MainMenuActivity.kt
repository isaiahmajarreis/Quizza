package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.content.res.Resources
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

const val EXTRA_MESSAGE_TOKEN = "com.mobdeve.majarreisroncal.quizza.msg.token"

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private val loadTokenExecutor = Executors.newSingleThreadExecutor()
    var token: String? = null

    // screen dimensions
    private val width = Resources.getSystem().displayMetrics.widthPixels
    private val height = Resources.getSystem().displayMetrics.heightPixels

    // speed
    var pizzaSpeed = (5..10).random().toFloat()
    var burgerSpeed = (5..10).random().toFloat()
    var drinkSpeed = (5..10).random().toFloat()
    var sauceSpeed = (5..10).random().toFloat()
    var hotdogSpeed = (5..10).random().toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        loadToken()

        val clickMp = MediaPlayer.create(this, R.raw.buttonclick)

        // initial positions
        binding.ivPizza.x = -90.0f
        binding.ivBurger.x = -90.0f
        binding.ivDrink.x = -90.0f
        binding.ivSauce.x = -90.0f
        binding.ivHotdog.x = -90.0f

        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post { changePos() }
            }
        }, 0, 20)

        binding.btnPlay.setOnClickListener {
            clickMp.start()

            val play = PlayActivity::class.java
            val goToPlay = Intent(this, play)
                .apply { putExtra(EXTRA_MESSAGE_TOKEN, token)}
            startActivity(goToPlay)
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

    private fun changePos() {
        // pizza
        binding.ivPizza.x += pizzaSpeed
        binding.ivPizza.y += pizzaSpeed

        if(binding.ivPizza.x > width || binding.ivPizza.y > height) {
            val rand = (0..1).random()
            pizzaSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivPizza.x = (-90..width).random().toFloat()
                binding.ivPizza.y = -90f
            }
            else {
                binding.ivPizza.x = -90f
                binding.ivPizza.y = (-90..height).random().toFloat()
            }
        }

        // burger
        binding.ivBurger.x += burgerSpeed
        binding.ivBurger.y += burgerSpeed

        if(binding.ivBurger.x > width || binding.ivBurger.y > height) {
            val rand = (0..1).random()
            burgerSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivBurger.x = (-90..width).random().toFloat()
                binding.ivBurger.y = -90f
            }
            else {
                binding.ivBurger.x = -90f
                binding.ivBurger.y = (-90..height).random().toFloat()
            }
        }

        // drink
        binding.ivDrink.x += drinkSpeed
        binding.ivDrink.y += drinkSpeed

        if(binding.ivDrink.x > width || binding.ivDrink.y > height) {
            val rand = (0..1).random()
            drinkSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivDrink.x = (-90..width).random().toFloat()
                binding.ivDrink.y = -90f
            }
            else {
                binding.ivDrink.x = -90f
                binding.ivDrink.y = (-90..height).random().toFloat()
            }
        }

        // sauce
        binding.ivSauce.x += sauceSpeed
        binding.ivSauce.y += sauceSpeed

        if(binding.ivSauce.x > width || binding.ivSauce.y > height) {
            val rand = (0..1).random()
            sauceSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivSauce.x = (-90..width).random().toFloat()
                binding.ivSauce.y = -90f
            }
            else {
                binding.ivSauce.x = -90f
                binding.ivSauce.y = (-90..height).random().toFloat()
            }
        }

        // hotdog
        binding.ivHotdog.x += hotdogSpeed
        binding.ivHotdog.y += hotdogSpeed

        if(binding.ivHotdog.x > width || binding.ivHotdog.y > height) {
            val rand = (0..1).random()
            hotdogSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivHotdog.x = (-90..width).random().toFloat()
                binding.ivHotdog.y = -90f
            }
            else {
                binding.ivHotdog.x = -90f
                binding.ivHotdog.y = (-90..height).random().toFloat()
            }
        }
    }

    private fun loadToken() {
        loadTokenExecutor.execute {
            try {
                token = OpenTrivia().getToken()
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "An error occurred please retry later: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}