package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.majarreisroncal.quizza.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val goToRegister = SpannableString("No account yet? Register here!")

        val span = object : ClickableSpan() {
            override fun onClick(p0: View) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.frag_account, RegisterFragment())
                    ?.commit()
            }
        }

        goToRegister.setSpan(span, 16, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val textToRegister = binding.tvToRegister
        textToRegister.text = goToRegister
        textToRegister.movementMethod = LinkMovementMethod.getInstance()

        binding.btnLogin.setOnClickListener {
            val goToMainMenu = Intent(activity, MainMenuActivity::class.java)
            activity?.startActivity(goToMainMenu)
        }

        return binding.root
    }

}