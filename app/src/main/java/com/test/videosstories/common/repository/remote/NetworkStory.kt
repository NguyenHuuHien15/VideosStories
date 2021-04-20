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

    @SerializedName("teaser")
    @Expose
    var teaser: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("date")
    @Expose
    var date: Double? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("sport")
    @Expose
    lateinit var sport: NetworkSport

    constructor() {}

    constructor(id: Int, title: String?, teaser: String?, image: String?, date: Double?, author: String?, sport: NetworkSport) {
        this.id = id
        this.title = title
        this.teaser = teaser
        this.image = image
        this.date = date
        this.author = author
        this.sport = sport
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}