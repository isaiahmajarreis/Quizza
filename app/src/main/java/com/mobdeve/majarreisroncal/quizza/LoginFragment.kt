package com.mobdeve.majarreisroncal.quizza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.majarreisroncal.quizza.databinding.FragmentGameBinding
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

        binding.btnLogin.setOnClickListener {

        }

        binding.tvRegister.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frag_account, RegisterFragment())
                ?.commit()
        }

        return binding.root
    }

}