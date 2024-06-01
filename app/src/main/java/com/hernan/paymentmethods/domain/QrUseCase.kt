package com.hernan.paymentmethods.domain

import com.hernan.paymentmethods.data.network.repo.QrRepository
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class QrUseCase @Inject constructor(
    private val qrRepository: QrRepository
) {
    suspend operator fun invoke(data: String):Call<ResponseBody> {
        return qrRepository.getQrCode(data)
    }

}