package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.core.database.LocalDataStore
import com.hernan.paymentmethods.databinding.FragmentCardBinding
import com.hernan.paymentmethods.domain.model.CreditCard
import com.hernan.paymentmethods.ui.adapters.CreditCardAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(layoutInflater, container, false)
        bottomNavView()
        getCardList()
        return binding.root
    }

    private fun getCardList() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            LocalDataStore.getCreditCard(requireContext()).collect { cardList ->
                launch(Dispatchers.Main) {
                    Log.d("LOCAL TOKEN", cardList.toString())
                    val adapter = CreditCardAdapter(cardList as ArrayList<CreditCard>, requireContext())
                    inflateRecycler(adapter)
                }
            }
        }
    }

    private fun inflateRecycler(adapter: CreditCardAdapter) {
        binding.apply {
            rvRelatedCards.layoutManager = LinearLayoutManager(requireContext())
            rvRelatedCards.setHasFixedSize(true)
            rvRelatedCards.adapter = adapter
        }
    }
    private fun bottomNavView() {
        binding.apply {
            bottomView.setOnItemSelectedListener { menuItem ->
                when (menuItem.toString()) {
                    getString(R.string.title_new_card) -> goToFragment(CardFragmentDirections.actionCardFragmentToAddNewCardFragment())
                    getString(R.string.title_qr_payment) -> goToFragment(CardFragmentDirections.actionCardFragmentToQrFragment())
                }
                true
            }
        }
    }

    private fun goToFragment(action: NavDirections) {
        findNavController().navigate(action)
    }

}