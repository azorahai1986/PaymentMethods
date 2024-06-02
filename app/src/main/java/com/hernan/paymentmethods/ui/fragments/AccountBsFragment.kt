package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.core.database.LocalDataStore
import com.hernan.paymentmethods.databinding.FragmentAccountBsBinding
import com.hernan.paymentmethods.domain.model.CreditCard
import com.hernan.paymentmethods.ui.validate
import com.hernan.paymentmethods.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountBsFragment : BottomSheetDialogFragment() {

    private val viewModel:AuthViewModel by viewModels()
    private lateinit var binding: FragmentAccountBsBinding
    private var validEmail = false
    private var validPassword = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBsBinding.inflate(layoutInflater, container, false)
        setUpViews()
        return binding.root
    }

    private fun setUpViews() {
        binding.apply {
            etUser.validate(
                validator = { input -> isValidEmail(input) },
                onValidStateChange = { isValid -> validEmail = isValid }
            )

            etPassword.validate(
                validator = { input -> isValidPassword(input) },
                onValidStateChange = { isValid -> validPassword = isValid }
            )

            btCreateAccount.setOnClickListener {
                if (validEmail && validPassword && etPersonName.text.isNotEmpty()) {
                    viewModel.createAccount(etUser.text.toString(), etPassword.text.toString(), requireActivity())
                    validateAccount()
                    savePersonName(etPersonName.text.toString())
                } else {
                    showToast("Alguno de los datos ingresados es erróneo o está vacío")
                }
            }
        }
    }

    private fun savePersonName(personName:String) {
        CoroutineScope(Dispatchers.IO).launch {
            LocalDataStore.savePersonName(requireContext(), personName)
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
               goToCardFragment()

            }
        })
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