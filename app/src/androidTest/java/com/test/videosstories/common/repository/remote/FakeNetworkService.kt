package com.test.videosstories.common.repository.remote

import javax.inject.Inject

class FakeNetworkService @Inject constructor() : INetworkService {

    private lateinit var sportFootball: NetworkSport
    private lateinit var sportVolley: NetworkSport
    private lateinit var networkVideo: NetworkVideo
    private lateinit var networkStory: NetworkStory

    override suspend fun getData(): NetworkItemsCollection {
        sportFootball = NetworkSport(111, "Football")
        sportVolley = NetworkSport(222, "Volley")
        networkVideo = NetworkVideo(
            1, "Video 1", "https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg",
            "https://vod-eurosport.akamaized.net/nogeo/2019/10/22/CHRONIQUE_FRITSCH_22102019_V1_22040825-1254400-2300-1024-576.mp4",
            11.1, sportFootball, 1000
        )
        networkStory = NetworkStory(
            2, "Story 2", "Volley teaser content",
            "https://i.eurosport.com/2020/04/29/2812626-58017546-640-220.png",
            22.2, "David", sportVolley
        )
        return NetworkItemsCollection(listOf(networkVideo), listOf(networkStory))
    }
}