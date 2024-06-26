package com.example.tvshowsapp.models.modelshow

import android.media.Rating
import android.net.Network
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowResponseItem(
    val averageRuntime: Int?,
    val ended: String?,
    val genres: List<String>?,
    val id: Int?,
    val image: Image?,
    val language: String?,
    val name: String?,
    val network: Network?,
    val officialSite: String?,
    val premiered: String?,
    val rating: Rating?,
    val runtime: Int?,
    val status: String?,
    val summary: String?,
    val type: String?,
    val updated: Int?,
    val url: String?,
    val weight: Int?
):Parcelable