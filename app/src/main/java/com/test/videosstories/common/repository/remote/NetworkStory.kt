package com.test.videosstories.common.repository.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NetworkStory : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    constructor() {}

    constructor(id: Int, title: String?, image: String?) : super() {
        this.id = id
        this.title = title
        this.image = image
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}