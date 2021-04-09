package com.test.videosstories.common.repository.source.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkItem(
    val id: Int,
    val title: String
)