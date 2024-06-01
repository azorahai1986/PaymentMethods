package com.hernan.paymentmethods.core

import com.hernan.paymentmethods.data.Constants
import com.hernan.paymentmethods.data.network.apiclient.QrApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Alcanse a nivel de aplicación
object NetworkModule {

    private val okHttpClient = OkHttpClient.Builder().build()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideQrApiClient(retrofit: Retrofit): QrApiClient {
        return retrofit.create(QrApiClient::class.java)
    }
}