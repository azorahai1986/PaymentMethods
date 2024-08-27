package com.hernan.paymentmethods.data.model

import kotlinx.serialization.Serializable


@Serializable
data class CreditCard(
    val cardImage:String,
    val cardName:String,
    val cardNumber:String,
    val cardSecurityCode:String,
    val endDate:String,
    val personName:String
)
