package com.test.videosstories.common.view

interface ITextSearchFilter<K : Any?> {
    fun shouldBeDisplayed(constraint: CharSequence?, obj: K): Boolean
}