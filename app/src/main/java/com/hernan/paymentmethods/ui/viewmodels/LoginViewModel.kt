package com.hernan.paymentmethods.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.hernan.paymentmethods.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
):ViewModel() {

    private val _login = MutableLiveData<Result<FirebaseUser>?>()
    val login: LiveData<Result<FirebaseUser>?> = _login

    fun loginWithUser(email:String, password:String) {
        viewModelScope.launch {
            _login.postValue(loginUseCase(email, password))

        }
    }
}