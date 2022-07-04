package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        binding.tvEmail.text = user?.email.toString()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Options"

        binding.tvLogout.setOnClickListener {
            auth.signOut()
            val goToLogin = Intent(this, LoginActivity::class.java)
            startActivity(goToLogin)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}