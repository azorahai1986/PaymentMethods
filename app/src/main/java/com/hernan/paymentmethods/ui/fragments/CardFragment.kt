package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.databinding.FragmentCardBinding
import com.hernan.paymentmethods.domain.model.CreditCard
import com.hernan.paymentmethods.ui.adapters.CreditCardAdapter
import com.hernan.paymentmethods.ui.viewmodels.QrCodeVm
import dagger.hilt.android.AndroidEntryPoint

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private lateinit var adapter:CreditCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(layoutInflater, container, false)
        inflateRecycler()
        bottomNavView()
        return binding.root
    }

    private fun bottomNavView() {
        binding.apply {
            bottomView.setOnItemSelectedListener { menuItem ->
                Log.d("BOTTOM ID", menuItem.toString())
                when (menuItem.toString()) {
                    getString(R.string.title_new_card) -> goToFragment(CardFragmentDirections.actionCardFragmentToAddNewCardFragment())
                    getString(R.string.title_qr_payment) -> goToFragment(CardFragmentDirections.actionCardFragmentToQrFragment())
                }
                true
            }
        }
    }

    private fun inflateRecycler() {
        adapter = CreditCardAdapter(cardList(), requireContext())
        binding.apply {
            rvRelatedCards.layoutManager = LinearLayoutManager(requireContext())
            rvRelatedCards.setHasFixedSize(true)
            rvRelatedCards.adapter = adapter
        }
    }

    private fun goToFragment(action: NavDirections) {
        findNavController().navigate(action)
    }

    fun cardList(): ArrayList<CreditCard> {
        return arrayListOf(
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561"),
            CreditCard("", "Visa", "1231 5565 7894 4561")
        )
    }

}