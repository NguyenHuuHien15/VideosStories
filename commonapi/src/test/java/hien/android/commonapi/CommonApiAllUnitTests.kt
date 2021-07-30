package hien.android.commonapi

import org.junit.runner.RunWith
import org.junit.runners.Suite

// Runs all unit tests.
@RunWith(Suite::class)
// @formatter:off
@Suite.SuiteClasses(
    ClassExtensionsTest::class,
)
// @formatter:on
class CommonApiAllUnitTests
