package com.test.videosstories

import com.test.videosstories.common.repository.local.ItemDaoTest
import com.test.videosstories.detail.viewmodel.StoryDetailsViewModelInstrumentedTest
import com.test.videosstories.list.view.ItemsCollectionFragmentTest
import com.test.videosstories.list.viewmodel.ItemsCollectionViewModelInstrumentedTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
// @formatter:off
@Suite.SuiteClasses(
    ItemDaoTest::class,
    ItemsCollectionViewModelInstrumentedTest::class,
    StoryDetailsViewModelInstrumentedTest::class,
    ItemsCollectionFragmentTest::class,
    AppTest::class,
    AppTestWithMockServer::class,
    AppTestWithMockServerError::class,
)
// @formatter:on
class AllInstrumentedTests {
}