package com.cbre.jday.githubexplorer_android.api

import com.cbre.jday.githubexplorer_android.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthAPI {

    @GET("user")
    fun login(@Header("Authorization") authHeader: String): Call<User>
}