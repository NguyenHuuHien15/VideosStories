package com.test.videosstories

import com.test.videosstories.common.repository.local.ItemDaoTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
// @formatter:off
@Suite.SuiteClasses(
    ItemDaoTest::class,
    AppTest::class
)
// @formatter:on
class AllInstrumentedTests {
}