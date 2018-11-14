package com.cbre.jday.githubexplorer_android.api

import com.cbre.jday.githubexplorer_android.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


typealias LoginResult = Result<User>

object LoginManager {

    val authAPI = RemoteRepository.authAPI()

    fun login(credential: AuthCredential, callback: (result: LoginResult) -> Unit) {
        authAPI.login(credential.encoded()).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response != null && response.isSuccessful) {

                    val user = response.body()
                    if (user != null) {
                        user.authCredentials = credential.encoded()
                        user.saveToSharedPrefs()

                        callback(Result(Status.SUCCESS, user, null))
                    } else {
                        callback(Result(Status.ERROR, null, LoginError.NO_USER))
                    }

                } else {
                    if (response.code() == 401) {
                        callback(Result(Status.ERROR, null, LoginError.INVALID_CREDENTIALS))
                    } else {
                        callback(Result(Status.ERROR, null, LoginError.SERVER))
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback(Result(Status.ERROR, null, LoginError.SERVER))
            }
        })
    }
}