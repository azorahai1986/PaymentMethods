package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.hernan.paymentmethods.databinding.FragmentQrBinding
import com.hernan.paymentmethods.ui.viewmodels.QrCodeVm
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class QrFragment : Fragment() {

    private lateinit var binding: FragmentQrBinding
    private val viewModelQr: QrCodeVm by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQrBinding.inflate(layoutInflater, container, false)
        viewModelQr.generateQrCode("John Doe")
        getQrCode()
        return binding.root
    }

    private fun getQrCode() {
        viewModelQr.qrCodeResult.observe(viewLifecycleOwner) { result ->
                result.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        val imageUrl = response.raw().request.url.toString()
                        Glide.with(requireContext()).load(imageUrl).into(binding.qrImage)
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }

                })

        }
    }

}