package com.test.videosstories.common.repository.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NetworkSport : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    constructor() {}

    constructor(id: Int, name: String?) : super() {
        this.id = id
        this.name = name
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}