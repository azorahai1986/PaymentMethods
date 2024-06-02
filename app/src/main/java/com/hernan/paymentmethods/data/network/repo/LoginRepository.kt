package com.hernan.paymentmethods.data.network.repo

import com.google.firebase.auth.FirebaseUser
import com.hernan.paymentmethods.data.network.api_firebase.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun loginWithUser(email: String, password: String): Result<FirebaseUser> {
        return loginService.enterWithLogin(email, password)
    }
}