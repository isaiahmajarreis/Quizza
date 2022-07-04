package com.mobdeve.majarreisroncal.quizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityHelpBinding
import java.util.*

class HelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpBinding
    private lateinit var tts : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        binding.btnTts.setOnClickListener {
            textToSpeech()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun textToSpeech() {
        tts = TextToSpeech(applicationContext) {
            if (it == TextToSpeech.SUCCESS) {
                tts.language = Locale.US
                tts.setSpeechRate(1.0f)
                tts.speak(
                    binding.tvDirections.text.toString(),
                    TextToSpeech.QUEUE_ADD,
                    null,
                    null
                )
            }
            else {
                Toast.makeText(
                    this,
                    "Text-to-Speech failed. Please try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}