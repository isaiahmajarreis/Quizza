package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainMenuBinding
import com.mobdeve.majarreisroncal.quizza.opentriviaAPI.OpenTrivia
import java.lang.Exception
import java.util.concurrent.Executors
import kotlin.system.exitProcess

const val EXTRA_MESSAGE_TOKEN = "com.mobdeve.majarreisroncal.quizza.msg.token"

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private val loadTokenExecutor = Executors.newSingleThreadExecutor()
    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        loadToken()

        binding.btnPlay.setOnClickListener {
            val play = PlayActivity::class.java
            val goToPlay = Intent(this, play)
                .apply { putExtra(EXTRA_MESSAGE_TOKEN, token)}
            startActivity(goToPlay)
        }

        binding.btnStats.setOnClickListener {
            // TODO: modal for stats
        }

        binding.btnOptions.setOnClickListener {
            var clickMp = MediaPlayer.create(this, R.raw.buttonclick)
            clickMp.start()
            val goToOptions = Intent(this, OptionsActivity::class.java)
            startActivity(goToOptions)
        }

        binding.btnHelp.setOnClickListener {
            val goToHelp = Intent(this, HelpActivity::class.java)
            startActivity(goToHelp)
        }


        binding.btnExit.setOnClickListener {
            // TODO: dialog popup alert on click ("Are you sure you want to exit the game?")
            moveTaskToBack(true)
            exitProcess(-1)
        }
    }

    private fun loadToken() {
        loadTokenExecutor.execute {
            try {
                token = OpenTrivia().getToken()
            } catch (e: Exception) {
                runOnUiThread(Runnable {
                    Toast.makeText(this, "An error occurred please retry later: ${e.message}", Toast.LENGTH_LONG).show()
                })
            }
        }


    }
}