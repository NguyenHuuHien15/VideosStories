package com.test.videosstories.common.repository.remote

import com.test.videosstories.list.model.ItemForView
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

val sportFootball = NetworkSport(111, "Football")
val sportVolley = NetworkSport(222, "Volley")

val networkVideo = NetworkVideo(
    1, "Video 1", "https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg",
    "https://vod-eurosport.akamaized.net/nogeo/2019/10/22/CHRONIQUE_FRITSCH_22102019_V1_22040825-1254400-2300-1024-576.mp4",
    11.1, sportFootball, 1000
)
val networkStory = NetworkStory(
    2, "Story 2", "Volley teaser content",
    "https://i.eurosport.com/2020/04/29/2812626-58017546-640-220.png",
    22.2, "David", sportVolley
)

val video = ItemForView(
    1, "Video 1", "https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg",
    "https://vod-eurosport.akamaized.net/nogeo/2019/10/22/CHRONIQUE_FRITSCH_22102019_V1_22040825-1254400-2300-1024-576.mp4",
    11.1, 111, "Football", 1000, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, true
)

val story = ItemForView(
    2, "Story 2", StringUtils.EMPTY, StringUtils.EMPTY, 22.2, 222, "Volley",
    null, "Volley teaser content",
    "https://i.eurosport.com/2020/04/29/2812626-58017546-640-220.png", "David", false
)

class FakeNetworkService @Inject constructor() : INetworkService {
    override suspend fun getData(): NetworkItemsCollection {
        return NetworkItemsCollection(listOf(networkVideo), listOf(networkStory))
    }
}