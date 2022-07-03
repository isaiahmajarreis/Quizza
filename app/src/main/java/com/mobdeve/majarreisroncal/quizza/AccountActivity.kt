package com.mobdeve.majarreisroncal.quizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        supportFragmentManager.beginTransaction().replace(R.id.frag_account, LoginFragment()).commit()
    }
}