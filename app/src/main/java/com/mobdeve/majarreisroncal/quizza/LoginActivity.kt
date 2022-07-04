package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val goToRegister = SpannableString("No account yet? Register here!")

        val span = object : ClickableSpan() {
            override fun onClick(p0: View) {
                goToRegister()
            }
        }

        goToRegister.setSpan(span, 16, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val textToRegister = binding.tvToRegister
        textToRegister.text = goToRegister
        textToRegister.movementMethod = LinkMovementMethod.getInstance()

        binding.btnLogin.setOnClickListener {
            val goToMainMenu = Intent(this, MainMenuActivity::class.java)
            startActivity(goToMainMenu)
        }
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}