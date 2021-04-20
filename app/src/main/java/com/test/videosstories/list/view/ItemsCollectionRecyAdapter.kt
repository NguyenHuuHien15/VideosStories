package com.test.videosstories.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.test.videosstories.common.view.GenericRecyListAdapter
import com.test.videosstories.common.view.IDiffItemCallback
import com.test.videosstories.common.view.ITextSearchFilter
import com.test.videosstories.databinding.RecyItemBinding
import com.test.videosstories.common.model.ItemForView
import com.test.videosstories.list.viewmodel.ItemViewModel
import com.test.videosstories.list.viewmodel.ItemsCollectionViewModel

class ItemsCollectionRecyAdapter(_context: Context, list: List<ItemForView>?, filter: ITextSearchFilter<ItemForView>?, diffCallback: IDiffItemCallback<ItemForView>) : GenericRecyListAdapter<ItemForView>(_context, list, filter, diffCallback) {

    private lateinit var itemsCollectionViewModel: ItemsCollectionViewModel
    private lateinit var lifecycleOwner: LifecycleOwner

    constructor(
        context: Context, list: List<ItemForView>?, filter: ITextSearchFilter<ItemForView>?, diffCallback: IDiffItemCallback<ItemForView>,
        itemsCollectionViewModel: ItemsCollectionViewModel, lifecycleOwner: LifecycleOwner
    ) : this(context, list, filter, diffCallback) {
        this.itemsCollectionViewModel = itemsCollectionViewModel
        this.lifecycleOwner = lifecycleOwner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: RecyItemBinding = RecyItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as MyViewHolder
        val element: ItemForView = currentList[position]
        holder.bind(itemsCollectionViewModel, element)
    }

    fun getItemPosition(item: ItemForView): Int {
        for (itemForView in currentList) {
            if (itemForView.id == item.id) {
                return currentList.indexOf(itemForView)
            }
        }
        return -1
    }

    inner class MyViewHolder(val binding: RecyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemsCollectionViewModel: ItemsCollectionViewModel, itemForView: ItemForView) {
            binding.lifecycleOwner = lifecycleOwner
            binding.viewmodel = itemsCollectionViewModel
            val viewModelBookItem = ItemViewModel()
            viewModelBookItem.setItem(itemForView)
            binding.viewModelItem = viewModelBookItem
            binding.executePendingBindings()
        }
    }
}