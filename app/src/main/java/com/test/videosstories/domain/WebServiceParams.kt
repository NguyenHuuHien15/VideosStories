package com.test.videosstories.domain

data class WebServiceParams(
    var protocol: String,
    var serverAddress: String,
    var port: Int,
    var baseUri: String,
    var connectTimeout: Long, // en secondes
    var readTimeout: Long, // en secondes
)