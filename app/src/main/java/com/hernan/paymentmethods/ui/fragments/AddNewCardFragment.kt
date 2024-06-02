package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.core.database.LocalDataStore
import com.hernan.paymentmethods.databinding.FragmentAddNewCardBinding
import com.hernan.paymentmethods.domain.model.CreditCard
import com.hernan.paymentmethods.ui.creditCardDate
import com.hernan.paymentmethods.ui.creditCardName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewCardFragment : Fragment() {

    private var personName: String? = null
    private lateinit var binding: FragmentAddNewCardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewCardBinding.inflate(layoutInflater, container, false)
        binding.btAddCard.setOnClickListener { setNewCard() }

        setNumberToCardName()
        getUserName()
        return binding.root
    }
    fun getUserName() {
        CoroutineScope(Dispatchers.IO).launch {
            LocalDataStore.getPersonName(requireContext()).collect { name ->
                personName = name
            }
        }
    }
    private fun setNumberToCardName() {
        binding.etCardNumber.creditCardName(binding.tvCardName)
        binding.etDate.creditCardDate()
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

            if (etPersonName.text.toString() == personName) {
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    LocalDataStore.saveCreditCard(requireContext(), creditCard)
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.difrent_names), Toast.LENGTH_SHORT).show()
            }

        }
        findNavController().popBackStack()
    }

}