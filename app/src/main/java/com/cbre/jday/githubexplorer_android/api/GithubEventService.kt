package com.cbre.jday.githubexplorer_android.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cbre.jday.githubexplorer_android.model.APIError
import com.cbre.jday.githubexplorer_android.model.GithubError
import com.cbre.jday.githubexplorer_android.model.GithubEvent
import com.cbre.jday.githubexplorer_android.model.Result
import com.cbre.jday.githubexplorer_android.util.auth.AuthManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


typealias EventResult = Result<List<GithubEvent>>

class GithubEventService {

    val githubAPI = RemoteRepository.githubAPI()



    fun fetchEvents(): LiveData<Result<List<GithubEvent>>> {
        val liveData = MutableLiveData<Result<List<GithubEvent>>>()

        AuthManager.getAuthUser()?.let { user ->
            githubAPI.getEvents(user.authCredentials ?: "", "facebook"/*user.username*/).enqueue(object : Callback<List<GithubEvent>> {
                override fun onResponse(call: Call<List<GithubEvent>>, response: Response<List<GithubEvent>>) {
                    if (response != null && response.isSuccessful) {
                        liveData.value = Result.success(response.body())
                    } else {
                        liveData.value = Result.error(GithubError.GENERIC, null)
                    }
                }

                override fun onFailure(call: Call<List<GithubEvent>>, t: Throwable) {
                    liveData.value = Result.error(GithubError.GENERIC, null)
                }
            })
        } ?: return liveData

        return liveData
    }
}