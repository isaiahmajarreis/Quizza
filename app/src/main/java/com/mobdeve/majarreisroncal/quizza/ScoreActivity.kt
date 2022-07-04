package com.mobdeve.majarreisroncal.quizza

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityScoreBinding
import com.mobdeve.majarreisroncal.quizza.opentriviaAPI.OpenTrivia
import java.util.concurrent.Executors

/* displays the user's achieved score within
   the specified time limit */

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
        val shareScore = score.toString()

        /* gives the user an option to play again, which would send them
           back to the countdown timer and eventually, playActivity */
        binding.btnPlayAgain.setOnClickListener {
            clickMp.start()

            val countdown = CountdownActivity::class.java
            val goToCountdown = Intent(this, countdown)

            startActivity(goToCountdown)
        }

        /* enables the user to share the score that they achieved through
        plain text by creating a chooser, which enables users to relay
        information on installed apps on their device (mail, social media) */

        binding.btnShare.setOnClickListener {
            var shareMessage = "Check it out! I got a score of $shareScore in Quizza! Can you go higher? #QuizzaTrivia"
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", shareMessage)
            clipboard.setPrimaryClip(clip)

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share with: "))

        }

        /* gives the user an option to return to the main menu */
        binding.btnReturn.setOnClickListener() {
            val goToMainMenu = Intent(this, MainMenuActivity::class.java)
            startActivity(goToMainMenu)
        }
    }
}