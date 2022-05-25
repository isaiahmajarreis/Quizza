package com.mobdeve.majarreisroncal.quizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = ""
    }
}