package com.hernan.paymentmethods.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernan.paymentmethods.domain.QrUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class QrCodeVm @Inject constructor(
    private val qrUseCase:QrUseCase
): ViewModel() {

    private val _qrCodeResult = MutableLiveData<Call<ResponseBody>>()
    val qrCodeResult: LiveData<Call<ResponseBody>> = _qrCodeResult

    fun generateQrCode(data: String) {
        viewModelScope.launch {
            try {
                val result = qrUseCase(data)
                _qrCodeResult.postValue(result)
            } catch (e: Exception) {
                Log.d("EXEPTION VM", e.toString())
            }
        }
    }
}