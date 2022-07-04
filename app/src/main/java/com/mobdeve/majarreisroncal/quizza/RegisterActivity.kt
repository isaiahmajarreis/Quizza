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
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityRegisterBinding

/**
 * Activity that allows user registration.
 * The credentials are then stored into the database.
 */
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        clickableText()

        /**
         * Initial checking of errors before making Firebase itself check
         */
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirm.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirm.isNotEmpty()) {
                if(password == confirm)
                    register(email, password)
                else
                    Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Please fill all blank spaces.", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Allows only a portion of a TextView to be clickable, which leads to the LoginActivity
     */
    private fun clickableText() {
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
    }

    /**
     * Function for intent-ing to the LoginActivity
     */
    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    /**
     * Signs up the user to the database if there are no errors
     *
     * @param email: a string containing the user's email address
     * @param password: a string containing the user's password
     */
    private fun register(email : String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful)
                goToLogin()
            else
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
        }
    }
}