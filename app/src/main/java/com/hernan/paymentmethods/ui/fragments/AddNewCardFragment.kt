package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.hernan.paymentmethods.core.database.LocalDataStore
import com.hernan.paymentmethods.databinding.FragmentAddNewCardBinding
import com.hernan.paymentmethods.domain.model.CreditCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewCardFragment : Fragment() {

    private lateinit var binding: FragmentAddNewCardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewCardBinding.inflate(layoutInflater, container, false)
        binding.btAddCard.setOnClickListener { setNewCard() }


        return binding.root
    }

    private fun setNewCard() {
        binding.apply {
            val creditCard = CreditCard(
                "",
                tvCardName.text.toString(),
                etCardNumber.text.toString(),
                etSecurityCode.text.toString(),
                etDate.text.toString(),
                etPersonName.text.toString()
                )
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                LocalDataStore.saveCreditCard(requireContext(), creditCard)
            }
        }
    }

}