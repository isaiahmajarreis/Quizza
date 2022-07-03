package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Toast.makeText(this, "Connected to Firebase!", Toast.LENGTH_LONG).show();

        Handler(Looper.getMainLooper()).postDelayed({
            val backgroundMp = MediaPlayer.create(this, R.raw.bg)
            backgroundMp.start()
            backgroundMp.isLooping = true

            val goToMainMenu = Intent(this, MainMenuActivity::class.java)
            startActivity(goToMainMenu)
        }, 5000)
    }


}