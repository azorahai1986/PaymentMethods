package com.hernan.paymentmethods.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hernan.paymentmethods.data.UtilsLocalData
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
    ): View {

        binding = FragmentQrBinding.inflate(layoutInflater, container, false)
        UtilsLocalData.getUserName(requireContext())
        viewModelQr.generateQrCode(UtilsLocalData.personName)
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
                        if (imageUrl.isNotEmpty()) {

                            binding.progressBar.visibility = View.GONE
                            binding.qrImage.visibility = View.VISIBLE
                            Glide.with(requireContext()).load(imageUrl).into(binding.qrImage)

                        }
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }

                })

        }
    }

}