package com.hernan.paymentmethods.domain

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.hernan.paymentmethods.data.network.repo.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email:String, password:String, context: Activity): Task<AuthResult>? {
        return authRepository.getAuthService(email, password, context)
    }
}