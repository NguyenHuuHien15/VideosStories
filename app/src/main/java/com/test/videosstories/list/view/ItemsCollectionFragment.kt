package com.test.videosstories.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.videosstories.common.view.IDiffItemCallback
import com.test.videosstories.common.view.ITextSearchFilter
import com.test.videosstories.databinding.FragmentItemsCollectionBinding
import com.test.videosstories.list.model.ItemForView
import com.test.videosstories.list.viewmodel.ItemsCollectionViewModel

class ItemsCollectionFragment : Fragment() {
    val LOG_TAG = ItemsCollectionFragment::class.simpleName

    private lateinit var dataBinding: FragmentItemsCollectionBinding
    private lateinit var adapter: ItemsCollectionRecyAdapter

    private val viewModel: ItemsCollectionViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, ItemsCollectionViewModel.Factory(activity.application))
            .get(ItemsCollectionViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentItemsCollectionBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewmodel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Items collection"

        adapter = ItemsCollectionRecyAdapter(requireContext(), viewModel.itemsCollection.value, object : ITextSearchFilter<ItemForView> {
            override fun shouldBeDisplayed(constraint: CharSequence?, obj: ItemForView): Boolean {
                if (constraint == null || constraint.isEmpty()) return true
                val title = obj.title
                if (title == null || title.isEmpty()) return true

                val query = constraint.toString().toLowerCase()
                return (title.toLowerCase().contains(query))
            }
        }, object : IDiffItemCallback<ItemForView> {
            override fun areItemsTheSame(oldItem: ItemForView, newItem: ItemForView): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemForView, newItem: ItemForView): Boolean {
                return oldItem == newItem
            }
        }, viewModel, viewLifecycleOwner)

        val recyclerView = dataBinding.recyAllItems
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        viewModel.itemsCollection.observe(viewLifecycleOwner, { allItems ->
            allItems?.apply {
                val sorted = allItems.toMutableList().sortedByDescending { it.date }
                adapter.submitList(sorted)
            }
        })
    }
}