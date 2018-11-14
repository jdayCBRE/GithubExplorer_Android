package com.cbre.jday.githubexplorer_android.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.cbre.jday.githubexplorer_android.api.GithubEventService


class EventsViewModel(application: Application) : AndroidViewModel(application) {

    val eventService = GithubEventService()



    // TODO: how does this get called from the fragment?
    fun fetchEvents() = eventService.fetchEvents()
}