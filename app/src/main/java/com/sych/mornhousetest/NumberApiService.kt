package com.sych.mornhousetest

import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApiService {
    @GET("{number}")
    suspend fun getFact(@Path("number") number: String): retrofit2.Response<String>

    @GET("random/math")
    suspend fun getRandomFact(): retrofit2.Response<String>
}