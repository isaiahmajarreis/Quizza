package com.mobdeve.majarreisroncal.quizza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.btnRegister.setOnClickListener {

        }

        binding.tvLogin.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frag_account, LoginFragment())
                ?.commit()
        }

        return binding.root
    }

}