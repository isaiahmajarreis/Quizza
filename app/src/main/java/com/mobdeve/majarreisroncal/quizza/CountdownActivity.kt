package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityCountdownBinding
import com.mobdeve.majarreisroncal.quizza.opentriviaAPI.OpenTrivia
import java.util.concurrent.Executors

const val EXTRA_MESSAGE_TOKEN = "com.mobdeve.majarreisroncal.quizza.msg.token"

class CountdownActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountdownBinding
    private val loadTokenExecutor = Executors.newSingleThreadExecutor()
    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountdownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        loadToken()

        val time : Long = 3
        val timer = binding.tvCountdown
        var timeText = 0

        object : CountDownTimer(time * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText = millisUntilFinished.toInt() / 1000 + 1
                timer.text = timeText.toString()
            }
            override fun onFinish() {
                timer.text = resources.getString(R.string.start)
                intentToPlay()
            }
        }.start()
    }

    private fun loadToken() {
        loadTokenExecutor.execute {
            try {
                token = OpenTrivia().getToken()
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "An error occurred please retry later: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun intentToPlay() {
        Handler(Looper.getMainLooper()).postDelayed({
            val play = PlayActivity::class.java
            val goToPlay = Intent(this, play)
                .apply { putExtra(EXTRA_MESSAGE_TOKEN, token) }

            startActivity(goToPlay)
        }, 1000)
    }
}