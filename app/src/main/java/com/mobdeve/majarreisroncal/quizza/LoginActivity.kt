package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityLoginBinding

/**
 * Activity for the user to login. This is the first activity to be shown right
 * after the splash screen, given that none has been logged in
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        clickableText()

        /**
         * Initial checking of errors before making Firebase itself check
         */
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty())
                login(email, password)
            else
                Toast.makeText(this, "Please fill all blank spaces.", Toast.LENGTH_LONG).show()

        }
    }

    /**
     * Allows only a portion of a TextView to be clickable, which leads to the RegisterActivity
     */
    private fun clickableText() {
        val spanRegister = SpannableString("No account yet? Register here!")

        val span = object : ClickableSpan() {
            override fun onClick(p0: View) {
                goToRegister()
            }
        }

        spanRegister.setSpan(span, 16, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val textToRegister = binding.tvToRegister
        textToRegister.text = spanRegister
        textToRegister.movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * Function for intent-ing to the RegisterActivity
     */
    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    /**
     * Logs the user in if there are no errors
     *
     * @param email: a string containing the user's email address
     * @param password: a string containing the user's password
     */
    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                val goToMainMenu = Intent(this, MainMenuActivity::class.java)
                startActivity(goToMainMenu)
            }
            else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}