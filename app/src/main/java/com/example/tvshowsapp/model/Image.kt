package com.example.tvshowsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val medium: String,
    val original: String
):Parcelable