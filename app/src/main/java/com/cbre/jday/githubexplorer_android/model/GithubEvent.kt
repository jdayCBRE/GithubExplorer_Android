package com.cbre.jday.githubexplorer_android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class GithubEvent(
        val id: String,
        val actor: EventActor,
        @SerializedName("created_at") val creationDate: Date,
        val payload: EventPayload,
        @SerializedName("public") val isPublic: Boolean,
        val repo: EventRepo,
        val type: String
): Parcelable {
}