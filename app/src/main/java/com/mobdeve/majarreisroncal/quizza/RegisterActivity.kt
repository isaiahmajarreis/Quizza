package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val spanLogin = SpannableString("Already have an account?")

        val span = object : ClickableSpan() {
            override fun onClick(p0: View) {
                goToLogin()
            }
        }

        spanLogin.setSpan(span, 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanLogin.setSpan(ForegroundColorSpan(Color.RED), 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val textToLogin = binding.tvToLogin
        textToLogin.text = spanLogin
        textToLogin.movementMethod = LinkMovementMethod.getInstance()

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text
            val password = binding.etPassword.text
            val confirm = binding.etConfirm.text

            if(password.toString() == (confirm.toString())) {
                // TODO: implement addition of account to firebase
                goToLogin()
            }
            else {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}