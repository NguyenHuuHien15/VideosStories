package com.test.videosstories;

import com.test.videosstories.common.repository.ItemsRepoTest;
import com.test.videosstories.common.repository.local.entity.ItemEntityTest;
import com.test.videosstories.common.util.UtilTest;
import com.test.videosstories.detail.viewmodel.StoryDetailsViewModelTest;
import com.test.videosstories.list.viewmodel.ItemsCollectionViewModelTest;
import com.test.videosstories.playvideo.viewmodel.PlayerVideoViewModel;
import com.test.videosstories.playvideo.viewmodel.PlayerVideoViewModelTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({
        ItemsCollectionViewModelTest.class,
        ItemEntityTest.class,
        UtilTest.class,
        StoryDetailsViewModelTest.class,
        PlayerVideoViewModelTest.class,
        ItemsRepoTest.class,
        //RoboAppTestWithMockServer.class,
})
// @formatter:on
public class AllUnitTests {
}
