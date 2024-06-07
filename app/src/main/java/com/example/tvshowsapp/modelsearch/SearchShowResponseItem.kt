package com.example.tvshowsapp.modelsearch

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchShowResponseItem(
    val score: Double?,
    val show: Show?
):Parcelable