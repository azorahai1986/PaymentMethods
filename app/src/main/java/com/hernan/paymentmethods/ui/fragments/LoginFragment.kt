package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hernan.paymentmethods.databinding.FragmentCardBinding
import com.hernan.paymentmethods.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        setUpViews()
        return binding.root
    }

    private fun setUpViews() {
        binding.apply {
            tvAccount.setOnClickListener { goToAccount() }
            btnLogin.setOnClickListener { goToCardFragment() }
        }
    }

    private fun goToAccount() {
        AccountBsFragment().show(parentFragmentManager, "Account")
    }

    private fun goToCardFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToCardFragment()
        findNavController().navigate(action)
    }
}