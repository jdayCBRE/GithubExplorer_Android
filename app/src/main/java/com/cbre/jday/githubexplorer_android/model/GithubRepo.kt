package com.cbre.jday.githubexplorer_android.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(
        @SerializedName("full_name") val fullName: String,
        @SerializedName("stargazers_count") val stargazerCount: Int,
        val forks: Int,
        @SerializedName("open_issues") val openIssues: Int
) {
}