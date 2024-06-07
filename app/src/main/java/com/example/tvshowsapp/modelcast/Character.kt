package com.example.tvshowsapp.modelcast

data class Character(
    val _links: Links,
    val id: Int,
    val image: Image,
    val name: String,
    val url: String
)