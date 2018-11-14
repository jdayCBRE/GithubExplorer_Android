package com.cbre.jday.githubexplorer_android.api

import com.cbre.jday.githubexplorer_android.model.GithubEvent
import com.cbre.jday.githubexplorer_android.model.GithubRepoItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {

    @GET("users/{username}/events")
    fun getEvents(@Header("Authorization") authHeader: String, @Path("username") username: String): Call<List<GithubEvent>>

    @GET("search/repositories")
    fun searchEvents(@Query("q") query: String): Call<GithubRepoItems>
}