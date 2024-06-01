package com.hernan.paymentmethods.data.network.repo

import com.hernan.paymentmethods.data.network.apiclient.QrApiService
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class QrRepository @Inject constructor(
    private val apiService: QrApiService,
) {
    suspend fun getQrCode(data: String): Call<ResponseBody> {
        return apiService.getQrCode(data)
    }
}