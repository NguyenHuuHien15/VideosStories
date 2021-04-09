package com.test.videosstories.common.repository.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NetworkVideo : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

    constructor() {}

    constructor(id: Int, title: String?, thumb: String?) : super() {
        this.id = id
        this.thumb = thumb
        this.title = title
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}