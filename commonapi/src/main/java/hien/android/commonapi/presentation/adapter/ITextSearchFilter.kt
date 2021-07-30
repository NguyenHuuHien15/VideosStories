package hien.android.commonapi.presentation.adapter

interface ITextSearchFilter<K> {
    fun shouldBeDisplayed(constraint: CharSequence?, obj: K): Boolean
}