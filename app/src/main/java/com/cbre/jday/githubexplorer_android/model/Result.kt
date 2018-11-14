package com.cbre.jday.githubexplorer_android.model



data class Result<out T>(val status: Status, val data: T?, val error: APIError?) {

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(error: APIError, data: T?): Result<T> {
            return Result(Status.ERROR, data, error)
        }
    }
}