package com.cbre.jday.githubexplorer_android.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Parcelize
data class EventRepo(val id: Int, val name: String, val url: URL): Parcelable {
}