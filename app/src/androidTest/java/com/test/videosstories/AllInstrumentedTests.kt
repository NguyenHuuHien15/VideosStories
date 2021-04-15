package com.test.videosstories

import com.test.videosstories.common.repository.local.ItemDaoTest
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
    AppTest::class,
)
// @formatter:on
class AllInstrumentedTests {
}