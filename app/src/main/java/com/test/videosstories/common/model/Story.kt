package com.test.videosstories.common.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Story : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("image")
    @Expose
    var image: String? = null

    constructor() {}

    constructor(id: Int, image: String?) : super() {
        this.id = id
        this.image = image
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}