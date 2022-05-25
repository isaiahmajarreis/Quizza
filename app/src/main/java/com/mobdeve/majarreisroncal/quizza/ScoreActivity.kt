package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityHelpBinding
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val score = intent.getIntExtra("score", 0)
        val textViewScore = findViewById<TextView>(R.id.tv_score_num)
        textViewScore.text = score.toString()

        binding.btnPlayAgain.setOnClickListener {
            val goToPlay = Intent(this, PlayActivity::class.java)
            startActivity(goToPlay)
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