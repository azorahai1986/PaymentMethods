package com.hernan.paymentmethods.data.network.api_firebase

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class AuthService @Inject constructor(
    private val auth: AuthFirebase
) {
    suspend fun getAuthService(email:String, password:String, context: Activity): Task<AuthResult> {
        return suspendCancellableCoroutine { continuation ->
            auth.authFirebase().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        continuation.resume(task, null)
                    } else {
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }


}