package com.example.tvshowsapp.models.modelepisode

import android.os.Parcelable
import com.example.tvshowsapp.models.modelcast.Links
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeResponseItem(
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: Image,
    val name: String,
    val number: Int,
    val runtime: Int,
    val season: Int,
    val summary: String,
    val type: String,
    val url: String
):Parcelable