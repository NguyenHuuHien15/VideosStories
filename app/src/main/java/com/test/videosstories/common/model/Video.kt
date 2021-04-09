package com.test.videosstories.common.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Video : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

    constructor() {}

    constructor(id: Int, thumb: String?) : super() {
        this.id = id
        this.thumb = thumb
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}