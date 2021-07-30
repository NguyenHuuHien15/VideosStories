package com.test.videosstories.domain

data class Story(
    val id: Int,
    val title: String,
    val teaser: String,
    val image: String,
    val date: Double,
    val author: String,
    val sport: Sport
)