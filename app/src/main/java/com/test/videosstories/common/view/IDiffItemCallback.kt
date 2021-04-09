package com.test.videosstories.common.view

interface IDiffItemCallback<K : Any?> {
    fun areItemsTheSame(oldItem: K, newItem: K): Boolean
    fun areContentsTheSame(oldItem: K, newItem: K): Boolean
}