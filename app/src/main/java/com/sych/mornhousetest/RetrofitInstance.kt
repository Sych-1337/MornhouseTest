package com.sych.mornhousetest

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    val api: NumberApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumberApiService::class.java)
    }
}
