package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.core.database.LocalDataStore
import com.hernan.paymentmethods.data.UtilsLocalData
import com.hernan.paymentmethods.databinding.FragmentLoginBinding
import com.hernan.paymentmethods.ui.validate
import com.hernan.paymentmethods.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var isActive: Boolean = false
    private lateinit var binding: FragmentLoginBinding
    private var auth: FirebaseAuth = Firebase.auth
    private val viewModelLogin:LoginViewModel by viewModels()
    private var validEmail = false
    private var validPassword = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        setUpViews()
        getUserName()
        setTextStyle()
        return binding.root
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToCardFragment()
        }
    }

    fun getUserName() {
        UtilsLocalData.getUserName(requireContext())
    }

    private fun setUpViews() {
        binding.apply {
            textInputEmail.etText.validate(
                validator = { input -> isValidEmail(input) },
                onValidStateChange = { isValid -> validEmail = isValid }
            )

            textInputPassword.etText.validate(
                    validator = { input -> isValidPassword(input) },
                    onValidStateChange = { isValid -> validPassword = isValid }
                )

            tvAccount.setOnClickListener { goToAccount() }
            btnLogin.setOnClickListener {
                if (isActive && textInputname.etText.text.toString().isEmpty()) {
                    showToast("Debes ingresar el nombre del titular de las tarjetas registradas")

                } else if (validEmail && validPassword) {
                    viewModelLogin.loginWithUser(
                        textInputEmail.etText.text.toString(),
                        textInputPassword.etText.text.toString()
                    )
                    validateLogin()
                    viewLifecycleOwner.lifecycleScope.launch {
                        if (UtilsLocalData.personName.isEmpty()) LocalDataStore.savePersonName(
                            requireContext(),
                            binding.textInputname.etText.text.toString().trim())
                    }
                } else {
                    showToast("Alguno de los datos ingresados es erróneo o está vacío")
                }
            }

        }
    }

    private fun validateLogin() {
        binding.progress.visibility = View.VISIBLE
        viewModelLogin.login.observe(viewLifecycleOwner, Observer {
                if (it != null && it.isSuccess) {
                    goToCardFragment()
                }else {
                    showToast("Algo Salió mal. comprueba que estás registrado")
                    binding.progress.visibility = View.GONE
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

    private fun setTextStyle() {
        binding.apply {

            textInputname.apply {
                if (UtilsLocalData.personName.isEmpty()) {
                    textInput.visibility = View.VISIBLE
                    isActive = true
                } else {
                    textInput.visibility = View.GONE
                }
                textInput.hint = getString(R.string.person_name)
                textInput.isHintAnimationEnabled = true
                etText.requestFocus()
            }

            textInputEmail.apply {
                textInput.hint = getString(R.string.input_your_email)
                textInput.isHintAnimationEnabled = true
                etText.requestFocus()
                etText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

            }

            textInputPassword.apply {
                textInput.startIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_lock)
                textInput.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                etText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                textInput.hint = getString(R.string.password)
                textInput.isHintAnimationEnabled = true
            }
        }
    }
}