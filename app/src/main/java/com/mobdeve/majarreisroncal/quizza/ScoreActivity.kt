package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityScoreBinding
import com.mobdeve.majarreisroncal.quizza.opentriviaAPI.OpenTrivia
import java.util.concurrent.Executors

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val clickMp = MediaPlayer.create(this, R.raw.buttonclick)
        val score = intent.getIntExtra("score", 0)
        val textViewScore = findViewById<TextView>(R.id.tv_score_num)
        textViewScore.text = score.toString()

        binding.btnPlayAgain.setOnClickListener {
            clickMp.start()

            val countdown = CountdownActivity::class.java
            val goToCountdown = Intent(this, countdown)

            startActivity(goToCountdown)
        }

        binding.btnShare.setOnClickListener {
            // TODO: share score to facebook
        }

        binding.btnReturn.setOnClickListener() {
            val goToMainMenu = Intent(this, MainMenuActivity::class.java)
            startActivity(goToMainMenu)
        }
    }
}