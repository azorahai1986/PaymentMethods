package com.hernan.paymentmethods.data.network.repo

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.hernan.paymentmethods.data.network.api_firebase.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
    suspend fun getAuthService(email:String, password:String, context:Activity): Task<AuthResult> =
        authService.getAuthService(email, password, context)
}