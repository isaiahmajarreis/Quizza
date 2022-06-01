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
    var mushroomSpeed = (5..10).random().toFloat()
    var tomatoSpeed = (5..10).random().toFloat()
    var bellPepperSpeed = (5..10).random().toFloat()
    var onionSpeed = (5..10).random().toFloat()
    var garlicSpeed = (5..10).random().toFloat()
    var cheeseSpeed = (5..10).random().toFloat()
    var pepperoniSpeed = (5..10).random().toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        loadToken()

        val clickMp = MediaPlayer.create(this, R.raw.buttonclick)

        // initial positions
        binding.ivMushroom.x = -90f
        binding.ivTomato.x = -90f
        binding.ivBellPepper.x = -90f
        binding.ivOnion.x = -90f
        binding.ivGarlic.x = -90f
        binding.ivCheese.x = -90f
        binding.ivPepperoni.x = -90f

        binding.ivMushroom.y = -90f
        binding.ivTomato.y = -90f
        binding.ivBellPepper.y = -90f
        binding.ivOnion.y = -90f
        binding.ivGarlic.y = -90f
        binding.ivCheese.y = -90f
        binding.ivPepperoni.y = -90f

        val rand = (0..1).random()
        if(rand == 0) {
            binding.ivMushroom.x = (-90..width).random().toFloat()
            binding.ivTomato.x = (-90..width).random().toFloat()
            binding.ivBellPepper.x = (-90..width).random().toFloat()
            binding.ivOnion.x = (-90..width).random().toFloat()
            binding.ivGarlic.x = (-90..width).random().toFloat()
            binding.ivCheese.x = (-90..width).random().toFloat()
            binding.ivPepperoni.x = (-90..width).random().toFloat()
        }
        else {
            binding.ivMushroom.y = (-90..height).random().toFloat()
            binding.ivTomato.y = (-90..height).random().toFloat()
            binding.ivBellPepper.y = (-90..height).random().toFloat()
            binding.ivOnion.y = (-90..height).random().toFloat()
            binding.ivGarlic.y = (-90..height).random().toFloat()
            binding.ivCheese.y = (-90..height).random().toFloat()
            binding.ivPepperoni.y = (-90..height).random().toFloat()
        }

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
        // mushroom
        binding.ivMushroom.x += mushroomSpeed
        binding.ivMushroom.y += mushroomSpeed

        if(binding.ivMushroom.x > width || binding.ivMushroom.y > height) {
            val rand = (0..1).random()
            mushroomSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivMushroom.x = (-90..width).random().toFloat()
                binding.ivMushroom.y = -90f
            }
            else {
                binding.ivMushroom.x = -90f
                binding.ivMushroom.y = (-90..height).random().toFloat()
            }
        }

        // tomato
        binding.ivTomato.x += tomatoSpeed
        binding.ivTomato.y += tomatoSpeed

        if(binding.ivTomato.x > width || binding.ivTomato.y > height) {
            val rand = (0..1).random()
            tomatoSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivTomato.x = (-90..width).random().toFloat()
                binding.ivTomato.y = -90f
            }
            else {
                binding.ivTomato.x = -90f
                binding.ivTomato.y = (-90..height).random().toFloat()
            }
        }

        // bell pepper
        binding.ivBellPepper.x += bellPepperSpeed
        binding.ivBellPepper.y += bellPepperSpeed

        if(binding.ivBellPepper.x > width || binding.ivBellPepper.y > height) {
            val rand = (0..1).random()
            bellPepperSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivBellPepper.x = (-90..width).random().toFloat()
                binding.ivBellPepper.y = -90f
            }
            else {
                binding.ivBellPepper.x = -90f
                binding.ivBellPepper.y = (-90..height).random().toFloat()
            }
        }

        // onion
        binding.ivOnion.x += onionSpeed
        binding.ivOnion.y += onionSpeed

        if(binding.ivOnion.x > width || binding.ivOnion.y > height) {
            val rand = (0..1).random()
            onionSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivOnion.x = (-90..width).random().toFloat()
                binding.ivOnion.y = -90f
            }
            else {
                binding.ivOnion.x = -90f
                binding.ivOnion.y = (-90..height).random().toFloat()
            }
        }

        // garlic
        binding.ivGarlic.x += garlicSpeed
        binding.ivGarlic.y += garlicSpeed

        if(binding.ivGarlic.x > width || binding.ivGarlic.y > height) {
            val rand = (0..1).random()
            garlicSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivGarlic.x = (-90..width).random().toFloat()
                binding.ivGarlic.y = -90f
            }
            else {
                binding.ivGarlic.x = -90f
                binding.ivGarlic.y = (-90..height).random().toFloat()
            }
        }

        // cheese
        binding.ivCheese.x += cheeseSpeed
        binding.ivCheese.y += cheeseSpeed

        if(binding.ivCheese.x > width || binding.ivCheese.y > height) {
            val rand = (0..1).random()
            cheeseSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivCheese.x = (-90..width).random().toFloat()
                binding.ivCheese.y = -90f
            }
            else {
                binding.ivCheese.x = -90f
                binding.ivCheese.y = (-90..height).random().toFloat()
            }
        }

        // pepperoni
        binding.ivPepperoni.x += pepperoniSpeed
        binding.ivPepperoni.y += pepperoniSpeed

        if(binding.ivPepperoni.x > width || binding.ivPepperoni.y > height) {
            val rand = (0..1).random()
            pepperoniSpeed = (5..10).random().toFloat()

            if(rand == 0) {
                binding.ivPepperoni.x = (-90..width).random().toFloat()
                binding.ivPepperoni.y = -90f
            }
            else {
                binding.ivPepperoni.x = -90f
                binding.ivPepperoni.y = (-90..height).random().toFloat()
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