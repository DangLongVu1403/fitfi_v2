package com.example.ff.Interface

import com.example.ff.Models.ApiResponse
import com.example.ff.Models.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/users/login")
    fun loginUser(@Body requestBody: LoginRequest): Call<ApiResponse>
}