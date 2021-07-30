package hien.android.commonapi

import hien.android.commonapi.presentation.GraphicUtilTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
// @formatter:off
@Suite.SuiteClasses(
    AppContextTest::class,
    GraphicUtilTest::class,
    AppUtilTest::class,
    ClassExtensionsInstrumentedTest::class,
)
// @formatter:on
class CommonApiAllInstrumentedTests