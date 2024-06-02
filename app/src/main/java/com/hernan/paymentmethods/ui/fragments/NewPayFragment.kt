package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.core.database.LocalDataStore
import com.hernan.paymentmethods.databinding.FragmentNewPayBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPayFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentNewPayBinding
    private val args: NewPayFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewPayBinding.inflate(layoutInflater, container, false)
        val cardNumber = args.cardNumber
        setView(cardNumber)
        return binding.root
    }

    private fun setView(creditCardNumber: String) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            LocalDataStore.getCreditCardByNumber(requireContext(), creditCardNumber).collect { cardList ->
               binding.apply {
                   if (cardList != null) {
                       tvCardNumber.text = cardList.cardNumber
                       tvCardName.text = cardList.cardName
                       tvPersonName.text = cardList.personName
                       tvCardDate.text = cardList.endDate
                   }
               }
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}