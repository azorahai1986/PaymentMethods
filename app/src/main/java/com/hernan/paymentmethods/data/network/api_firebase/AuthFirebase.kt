package com.hernan.paymentmethods.data.network.api_firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import javax.inject.Inject

class AuthFirebase @Inject constructor() {
    fun authFirebase() = Firebase.auth

}