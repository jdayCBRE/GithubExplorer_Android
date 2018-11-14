package com.cbre.jday.githubexplorer_android.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.cbre.jday.githubexplorer_android.api.GithubSearchService

class SearchResultsViewModel(application: Application) : AndroidViewModel(application) {

    val searchService = GithubSearchService()


    fun fetchSearchResults(searchQuery: String) = searchService.fetchSearchResults(searchQuery)
}