package com.cbre.jday.githubexplorer_android.model


interface APIError {
    fun message(): String
}


enum class LoginError: APIError {
    INVALID_CREDENTIALS,
    NO_USER,
    SERVER,
    UNKNOWN;

    override fun message(): String {
        return when (this) {
            INVALID_CREDENTIALS -> "Invalid username or password. Please try again."
            NO_USER             -> "Unable to retrieve user."
            SERVER              -> "An error occurred. Please check your internet connection and try again."
            UNKNOWN             -> "An error occurred. Please check your internet connection and try again."
        }
    }
}

enum class GithubError: APIError {
    GENERIC;

    override fun message(): String {
        return when (this) {
            else -> "Generic error message for now."
        }
    }
}