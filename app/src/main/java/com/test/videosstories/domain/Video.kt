package com.test.videosstories.domain

data class Video(
    val id: Int,
    val title: String,
    val thumb: String,
    val url: String,
    val date: Double,
    val sport: Sport,
    val views: Long
)