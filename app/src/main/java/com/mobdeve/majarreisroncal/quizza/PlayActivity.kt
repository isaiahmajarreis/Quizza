package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        var score = 31

        // TODO: fragments—one for countdown and one for actual game (maybe another for "time's up" screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val goToScore = Intent(this, ScoreActivity::class.java)
            goToScore.putExtra("score", score)
            startActivity(goToScore)
        }, 3000)
    }
}