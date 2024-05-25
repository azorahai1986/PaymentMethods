package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.databinding.FragmentAccountBsBinding

class AccountBsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAccountBsBinding
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
            btCreateAccount.setOnClickListener { goToCardFragment() }
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
}