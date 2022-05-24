package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        var backgroundMp = MediaPlayer.create(this, R.raw.bg)
        backgroundMp.start()

        binding.btnOptions.setOnClickListener {
            var clickMp = MediaPlayer.create(this, R.raw.buttonclick)
            clickMp.start()
            val goToOptions = Intent(this, OptionsActivity::class.java)
            startActivity(goToOptions)
        }
    }
}