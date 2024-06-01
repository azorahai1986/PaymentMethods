package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.text.Layout.Directions
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hernan.paymentmethods.databinding.FragmentCardBinding
import com.hernan.paymentmethods.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        val currentUser = auth.currentUser
        Log.d("CURRENT USER", currentUser.toString())
        setUpViews()
        return binding.root
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToCardFragment()
        }
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