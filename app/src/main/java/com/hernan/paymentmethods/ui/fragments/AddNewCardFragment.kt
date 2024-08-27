package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.core.database.LocalDataStore
import com.hernan.paymentmethods.data.UtilsLocalData
import com.hernan.paymentmethods.databinding.FragmentAddNewCardBinding
import com.hernan.paymentmethods.data.model.CreditCard
import com.hernan.paymentmethods.ui.activity.MainActivity
import com.hernan.paymentmethods.ui.creditCardDate
import com.hernan.paymentmethods.ui.creditCardName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewCardFragment : Fragment() {

    private lateinit var binding: FragmentAddNewCardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewCardBinding.inflate(layoutInflater, container, false)
        binding.btAddCard.setOnClickListener { setNewCard() }

        setNumberToCardName()
        getUserName()
        return binding.root
    }
    fun getUserName() {
        UtilsLocalData.getUserName(requireContext())
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

            val isAnyFieldEmpty = listOf(
                creditCard.cardName,
                creditCard.cardNumber,
                creditCard.cardSecurityCode,
                creditCard.endDate,
                creditCard.personName
            ).any { it.isBlank() }

            if (isAnyFieldEmpty) {
                (activity as? MainActivity)?.message(getString(R.string.empty_values))
            } else if (etPersonName.text.toString().trim() == UtilsLocalData.personName) {
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    LocalDataStore.saveCreditCard(requireContext(), creditCard)
                }
                findNavController().popBackStack()

            } else {
                (activity as? MainActivity)?.message(getString(R.string.difrent_names))
            }

        }
    }

}