package com.mobdeve.majarreisroncal.quizza

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobdeve.majarreisroncal.quizza.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val goToLogin = SpannableString("Already have an account?")

        val span = object : ClickableSpan() {
            override fun onClick(p0: View) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.frag_account, LoginFragment())
                    ?.commit()
            }
        }

        goToLogin.setSpan(span, 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        goToLogin.setSpan(ForegroundColorSpan(Color.RED), 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val textToLogin = binding.tvToLogin
        textToLogin.text = goToLogin
        textToLogin.movementMethod = LinkMovementMethod.getInstance()

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text
            val password = binding.etPassword.text
            val confirm = binding.etConfirm.text

            if(password.toString() == (confirm.toString())) {
                // TODO: implement addition of account to firebase

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.frag_account, LoginFragment())
                    ?.commit()
            }
            else {
                Toast.makeText(activity, "Passwords do not match!", Toast.LENGTH_LONG).show()
            }

        }

        return binding.root
    }

}