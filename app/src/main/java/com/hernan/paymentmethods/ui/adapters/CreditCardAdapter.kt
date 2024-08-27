package com.hernan.paymentmethods.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.databinding.ItemCreditCardBinding
import com.hernan.paymentmethods.data.model.CreditCard

class CreditCardAdapter(val cardList: ArrayList<CreditCard>, val context:Context, val iPosition:IPosition): RecyclerView.Adapter<CreditCardAdapter.ViewHolderCard>() {

    inner class ViewHolderCard(itemView: View):RecyclerView.ViewHolder(itemView) {
        val binding = ItemCreditCardBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCard =
        ViewHolderCard(LayoutInflater.from(parent.context).inflate(R.layout.item_credit_card, parent, false))


    override fun getItemCount(): Int = cardList.size

    override fun onBindViewHolder(holder: ViewHolderCard, position: Int) {
        val card = cardList[position]
        holder.binding.apply {
            tvCardName.text = card.cardName
            cardNumber.text = card.cardNumber
            constraintAdapter.setOnClickListener {
                iPosition.setCardPosition(card.cardNumber)

            }
        }
    }



    interface IPosition {
        fun setCardPosition(cardNumber: String)
    }

}