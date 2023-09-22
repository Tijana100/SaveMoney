package com.example.savemoney.view

import com.example.savemoney.model.Goals
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/859b78a7-ab92-41dd-9ac4-1d06a3bd2843")
    fun fetchAllGoals(): Call<List<Goals>>
}