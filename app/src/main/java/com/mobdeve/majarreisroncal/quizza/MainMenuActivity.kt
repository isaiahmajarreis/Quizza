package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupWindow
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainMenuBinding
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        var backgroundMp = MediaPlayer.create(this, R.raw.bg)
        backgroundMp.start()

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
}