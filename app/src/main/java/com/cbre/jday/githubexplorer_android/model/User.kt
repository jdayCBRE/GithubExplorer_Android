package com.cbre.jday.githubexplorer_android.model

import com.cbre.jday.githubexplorer_android.util.auth.AuthManager
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URL
import java.util.*



data class User(
        val id: String,
        @SerializedName("login") val username: String,
        val name: String,
        @SerializedName("avatar_url") val avatarUrl: URL,
        var authCredentials: String?) : Serializable {

    fun saveToSharedPrefs() {
        val gson = Gson()
        val jsonData = gson.toJson(this, User::class.java).toByteArray()
        val encoded = Base64.getEncoder().encodeToString(jsonData)

        AuthManager.saveAuthToken(encoded)
    }

    fun loadFromSharedPrefs() {
        AuthManager.getAuthUser().let {
            println("user from shared prefs: ${it}")
            return
        }

        println("unable to get user from shared prefs")
    }
}