package com.cbre.jday.githubexplorer_android.model

import java.util.*

data class AuthCredential(val username: String, val password: String) {

    fun encoded(): String {
        val authData = "${username}:${password}".toByteArray()
        val encodedCredential = "Basic ${Base64.getEncoder().encodeToString(authData)}"
        return encodedCredential
    }
}