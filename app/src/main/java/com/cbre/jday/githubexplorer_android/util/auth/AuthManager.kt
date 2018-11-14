package com.cbre.jday.githubexplorer_android.util.auth

import android.preference.PreferenceManager
import com.cbre.jday.githubexplorer_android.activity.app.GithubExplorer
import com.cbre.jday.githubexplorer_android.model.User
import com.google.gson.Gson
import java.util.*

object AuthManager {

    private const val KEY_AUTH_TOKEN = "KEY_AUTH_TOKEN"

    val authToken = sharedPrefs().getString(KEY_AUTH_TOKEN, "") ?: ""
    val isAuthenticated = !authToken.isBlank()

    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(GithubExplorer.getAppContext())


    fun saveAuthToken(token: String) {
        val editor = sharedPrefs().edit()
        editor.putString(KEY_AUTH_TOKEN, token).apply()
    }

    fun getAuthUser(): User? {
        sharedPrefs().getString(KEY_AUTH_TOKEN, "")?.let {
            val gson = Gson()
            val encoded = Base64.getDecoder().decode(it)
            val jsonString = String(encoded)
            val user = gson.fromJson<User>(jsonString, User::class.java)

            return user
        }

        return null
    }

    fun clearAuthToken() {
        sharedPrefs().edit().remove(KEY_AUTH_TOKEN).apply()
    }
}