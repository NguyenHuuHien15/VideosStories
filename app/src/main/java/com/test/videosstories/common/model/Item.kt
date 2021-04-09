package com.test.videosstories.common.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Item : Serializable {
    @SerializedName("videos")
    @Expose
    var videos: List<Video>? = null

    @SerializedName("stories")
    @Expose
    var stories: List<Story>? = null

    constructor() {}

    constructor(videos: List<Video>?, stories: List<Story>?) {
        this.videos = videos
        this.stories = stories
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}