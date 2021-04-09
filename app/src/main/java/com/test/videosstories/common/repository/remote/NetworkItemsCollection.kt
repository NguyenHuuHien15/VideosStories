package com.test.videosstories.common.repository.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NetworkItemsCollection : Serializable {
    @SerializedName("videos")
    @Expose
    var videos: List<NetworkVideo>? = null

    @SerializedName("stories")
    @Expose
    var stories: List<NetworkStory>? = null

    constructor() {}

    constructor(videos: List<NetworkVideo>?, stories: List<NetworkStory>?) {
        this.videos = videos
        this.stories = stories
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}