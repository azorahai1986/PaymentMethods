package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.databinding.FragmentAccountBsBinding
import com.hernan.paymentmethods.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountBsFragment : BottomSheetDialogFragment() {

    private val viewModel:AuthViewModel by viewModels()
    private lateinit var binding: FragmentAccountBsBinding
    private var auth: FirebaseAuth = Firebase.auth
    private var validEmail = false
    private var validPassword = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBsBinding.inflate(layoutInflater, container, false)
        requireDialog().setCanceledOnTouchOutside(false)
        requireDialog().setCancelable(false)
        setUpViews()



        return binding.root
    }

    private fun setUpViews() {
        binding.apply {
            etUser.addTextChangedListener(emailTextWatcher)
            etPassword.addTextChangedListener(passwordTextWatcher)
            btCreateAccount.setOnClickListener {
                if (validEmail && validPassword) {
                    viewModel.createAccount(etUser.text.toString(), etPassword.text.toString(), requireActivity())
                    validateAccount()
                } else {
                    showToast("Alguno de los datos ingresados es erroneo ó está vacio")
                }

            }
        }

    }

    private fun goToCardFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToCardFragment()
        findNavController().navigate(action)
        dismiss()
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun validateAccount() {
        viewModel.auth.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isSuccessful) {
                Log.d("USUARIO CREADO", it.isSuccessful.toString())
               goToCardFragment()

            }
        })
    }

    private val emailTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (!isValidEmail(s.toString())) {
                changeColor(binding.etUser, R.color.red)
            } else {
                changeColor(binding.etUser, R.color.black)
                validEmail = true
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (!isValidPassword(s.toString())) {
                changeColor(binding.etPassword, R.color.red)

            } else {
                changeColor(binding.etPassword, R.color.black)
                validPassword = true
            }
        }
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

    fun changeColor(e: EditText, color: Int) {
        e.setTextColor(ContextCompat.getColor(requireContext(), color))
    }
}