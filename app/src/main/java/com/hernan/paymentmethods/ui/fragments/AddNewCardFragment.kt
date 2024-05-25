package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.databinding.FragmentAddNewCardBinding

class AddNewCardFragment : Fragment() {

    private lateinit var binding: FragmentAddNewCardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewCardBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}