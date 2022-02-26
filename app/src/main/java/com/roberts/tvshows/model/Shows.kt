package com.roberts.tvshows.model

data class Shows(
    val id: Int,
    val image: Image,
    val language: String,
    val name: String,
    val premiered: String,
    val status: String,
    val summary: String,
    val type: String,
)