package com.example.jetpackcompose

import retrofit2.http.GET

interface Cricket {
    @GET("users")
    suspend fun getUsers(): List<Players>
}