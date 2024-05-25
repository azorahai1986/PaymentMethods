package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernan.paymentmethods.databinding.FragmentCardBinding
import com.hernan.paymentmethods.domain.CreditCard
import com.hernan.paymentmethods.ui.adapters.CreditCardAdapter

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
            /*bottomView.setOnItemSelectedListener { menuItem ->
                //goToAddNewCard(menuItem.itemId)
            }*/
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

    private fun goToAddNewCard(itemId: Int) {

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