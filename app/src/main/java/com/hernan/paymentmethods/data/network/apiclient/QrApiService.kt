package com.hernan.paymentmethods.data.network.apiclient

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class QrApiService @Inject constructor(
    private val apiClient: QrApiClient
) {

    suspend fun getQrCode(data: String): Call<ResponseBody> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.generateQrCode(data, "150x150")
            response
        }
    }

}