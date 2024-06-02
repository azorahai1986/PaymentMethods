package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hernan.paymentmethods.databinding.FragmentLoginBinding
import com.hernan.paymentmethods.ui.validate
import com.hernan.paymentmethods.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var auth: FirebaseAuth = Firebase.auth
    private val viewModelLogin:LoginViewModel by viewModels()
    private var validEmail = false
    private var validPassword = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

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
            etEmail.validate(
                validator = { input -> isValidEmail(input) },
                onValidStateChange = { isValid -> validEmail = isValid }
            )

            etPassword.validate(
                validator = { input -> isValidPassword(input) },
                onValidStateChange = { isValid -> validPassword = isValid }
            )

            tvAccount.setOnClickListener { goToAccount() }
            btnLogin.setOnClickListener {
                if (validEmail && validPassword) {
                    viewModelLogin.loginWithUser(etEmail.text.toString(), etPassword.text.toString())
                    validateLogin()
                } else {
                    showToast("Alguno de los datos ingresados es erróneo o está vacío")
                }
            }

        }
    }

    private fun validateLogin() {
        viewModelLogin.login.observe(viewLifecycleOwner, Observer {
                if (it != null && it.isSuccess) {
                    goToCardFragment()

                }
        })
    }

    private fun goToAccount() {
        AccountBsFragment().show(parentFragmentManager, "Account")
    }

    private fun goToCardFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToCardFragment()
        findNavController().navigate(action)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isLetter() }
    }

    fun showToast(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT,
        ).show()
    }
}