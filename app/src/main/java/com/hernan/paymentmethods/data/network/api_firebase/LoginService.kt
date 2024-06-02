package com.hernan.paymentmethods.data.network.api_firebase

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class LoginService @Inject constructor(
    private val authFirebase:AuthFirebase
) {
    suspend fun enterWithLogin(email: String, password: String): Result<FirebaseUser> {
        return try {
            val user = signInWithEmailAndPassword(email, password)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser {
        return suspendCancellableCoroutine { continuation ->
            authFirebase.authFirebase().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.user
                        if (user != null) {
                            continuation.resume(user, null)
                        } else {
                            continuation.resumeWithException(Exception("User is null"))
                        }
                    } else {
                        continuation.resumeWithException(task.exception ?: Exception("Authentication failed"))
                    }
                }
        }
    }
}