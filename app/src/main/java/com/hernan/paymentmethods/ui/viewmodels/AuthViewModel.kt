package com.hernan.paymentmethods.ui.viewmodels

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.hernan.paymentmethods.domain.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
):ViewModel() {

    private val _auth = MutableLiveData<Task<AuthResult>?>()
    val auth: LiveData<Task<AuthResult>?> = _auth

    fun createAccount(email:String, password:String, context: Activity) {
            viewModelScope.launch {
                _auth.postValue(authUseCase(email, password, context))
            }
    }
}