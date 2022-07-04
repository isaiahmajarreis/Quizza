package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityMainBinding

/**
 * A splash screen. This activity also checks whether there is a user logged in
 * or not, and intents to the correct activity depending on the case
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        lateinit var intent: Intent
        val currentUser = auth.currentUser

        intent =
            if(currentUser != null)
                Intent(this, MainMenuActivity::class.java)
            else
                Intent(this, LoginActivity::class.java)

        /**
         * Delays the intent in order to show the splash screen for a longer time
         */
        Handler(Looper.getMainLooper()).postDelayed({
            val backgroundMp = MediaPlayer.create(this, R.raw.bg)
            backgroundMp.start()
            backgroundMp.isLooping = true

            startActivity(intent)
        }, 5000)
    }


}