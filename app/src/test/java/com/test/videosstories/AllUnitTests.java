package com.test.videosstories;

import com.test.videosstories.list.viewmodel.ItemsCollectionViewModelTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({
        ExampleUnitTest.class,
        ItemsCollectionViewModelTest.class,
})
// @formatter:on
public class AllUnitTests {
}
