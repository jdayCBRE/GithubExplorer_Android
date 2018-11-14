package com.cbre.jday.githubexplorer_android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Parcelize
data class EventActor(
        @SerializedName("avatar_url") val avatarURL: URL,
        val login: String,
        val id: Int,
        val url: URL
): Parcelable {
}