package com.hernan.paymentmethods.data.network.apiclient

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QrApiClient {
    @GET("create-qr-code/")
    fun generateQrCode(
        @Query("data") data: String,
        @Query("size") size: String
    ): Call<ResponseBody>

}
