package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        Toast.makeText(this, "Connected to Firebase!", Toast.LENGTH_LONG).show()

        Handler(Looper.getMainLooper()).postDelayed({
            val backgroundMp = MediaPlayer.create(this, R.raw.bg)
            backgroundMp.start()
            backgroundMp.isLooping = true

            val goToAccount = Intent(this, AccountActivity::class.java)
            startActivity(goToAccount)
        }, 5000)
    }


}