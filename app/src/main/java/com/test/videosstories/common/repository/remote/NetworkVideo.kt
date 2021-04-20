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

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("date")
    @Expose
    var date: Double? = null

    @SerializedName("sport")
    @Expose
    lateinit var sport: NetworkSport

    @SerializedName("views")
    @Expose
    var views: Long? = null

    constructor() {}

    constructor(id: Int, title: String?, thumb: String?, url: String?, date: Double?, sport: NetworkSport, views: Long?) {
        this.id = id
        this.title = title
        this.thumb = thumb
        this.url = url
        this.date = date
        this.sport = sport
        this.views = views
    }

    companion object {
        private const val serialVersionUID = -2787315060106820541L
    }
}