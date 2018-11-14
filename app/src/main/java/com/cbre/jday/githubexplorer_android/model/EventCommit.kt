package com.cbre.jday.githubexplorer_android.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventCommit(val sha: String, val message: String): Parcelable {
}