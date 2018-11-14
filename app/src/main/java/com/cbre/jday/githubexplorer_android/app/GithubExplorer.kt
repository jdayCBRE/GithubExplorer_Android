package com.cbre.jday.githubexplorer_android.activity.app

import android.app.Application
import android.content.Context

class GithubExplorer : Application() {

    companion object {
        private lateinit var instance: GithubExplorer

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}
