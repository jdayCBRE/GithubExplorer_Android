package com.cbre.jday.githubexplorer_android.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cbre.jday.githubexplorer_android.model.GithubError
import com.cbre.jday.githubexplorer_android.model.GithubRepo
import com.cbre.jday.githubexplorer_android.model.GithubRepoItems
import com.cbre.jday.githubexplorer_android.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubSearchService {

    val githubAPI = RemoteRepository.githubAPI()

    fun fetchSearchResults(searchQuery: String): LiveData<Result<GithubRepoItems>> {
        val liveData = MutableLiveData<Result<GithubRepoItems>>()

        githubAPI.searchEvents(searchQuery).enqueue(object : Callback<GithubRepoItems> {
            override fun onResponse(call: Call<GithubRepoItems>, response: Response<GithubRepoItems>) {
                if (response != null && response.isSuccessful) {
                    liveData.value = Result.success(response.body())
                    println("response is good")
                } else {
                    liveData.value = Result.error(GithubError.GENERIC, null)
                    println("response is null or not successful")
                }
            }

            override fun onFailure(call: Call<GithubRepoItems>, t: Throwable) {
                liveData.value = Result.error(GithubError.GENERIC, null)
                println("request failed for repos: ${t.localizedMessage}")
            }
        })

        return liveData
    }
}