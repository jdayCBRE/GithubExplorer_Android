package com.cbre.jday.githubexplorer_android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventPayload(
        val description: String?,
        @SerializedName("master_branch") val masterBranch: String,
        @SerializedName("pusher_type") val pusherType: String,
        val ref: String?,
        @SerializedName("ref_type") val refType: String,
        val commits: List<EventCommit>?
): Parcelable {
}