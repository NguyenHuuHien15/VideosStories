package com.test.videosstories.common.model

data class ItemForView constructor(
    val id: Int,
    val title: String?,
    val thumb: String?,
    val url: String?,
    val date: Double?,
    val sportId: Int,
    val sportName: String,
    val views: Long?,
    val teaser: String?,
    val image: String?,
    val author: String?,
    val isVideo: Boolean
)