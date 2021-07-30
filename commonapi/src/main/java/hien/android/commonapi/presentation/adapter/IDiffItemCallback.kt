package hien.android.commonapi.presentation.adapter

interface IDiffItemCallback<K : Any?> {
    fun areItemsTheSame(oldItem: K, newItem: K): Boolean
    fun areContentsTheSame(oldItem: K, newItem: K): Boolean
}