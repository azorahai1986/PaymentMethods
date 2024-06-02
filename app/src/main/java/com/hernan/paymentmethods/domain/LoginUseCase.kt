package com.hernan.paymentmethods.domain

import com.google.firebase.auth.FirebaseUser
import com.hernan.paymentmethods.data.network.repo.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser> {
        return loginRepository.loginWithUser(email, password)
    }
}