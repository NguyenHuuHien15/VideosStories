package com.test.videosstories.list.view

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.videosstories.R
import com.test.videosstories.common.util.sortByDate
import com.test.videosstories.common.view.IDiffItemCallback
import com.test.videosstories.common.view.ITextSearchFilter
import com.test.videosstories.databinding.FragmentItemsCollectionBinding
import com.test.videosstories.common.model.ItemForView
import com.test.videosstories.list.viewmodel.ItemsCollectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsCollectionFragment : Fragment() {
    val LOG_TAG = ItemsCollectionFragment::class.simpleName

    private lateinit var dataBinding: FragmentItemsCollectionBinding
    private lateinit var adapter: ItemsCollectionRecyAdapter

    private val viewModel: ItemsCollectionViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentItemsCollectionBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.viewmodel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.show()
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.custom_actionbar)

        val window = activity?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //window?.insetsController?.show(WindowInsets.Type.statusBars())
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

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
            adapter.submitList(sortByDate(allItems))
        })

        viewModel.clickedItem.observe(viewLifecycleOwner, {
            it?.apply {
                if (it.isVideo) {
                    val action = ItemsCollectionFragmentDirections.actionItemsCollectionToPlayerVideo()
                    action.urlVideo = it.url.toString()
                    findNavController().navigate(action)
                    viewModel.doneNavigating()
                } else {
                    val action = ItemsCollectionFragmentDirections.actionItemsCollectionToStoryDetails()
                    action.itemId = it.id
                    findNavController().navigate(action)
                    viewModel.doneNavigating()
                }
            }
        })

        viewModel.needNotifyNetworkError.observe(viewLifecycleOwner, {
            if (it == true) {
                Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
                viewModel.doneNotifyNetworkError()
            }
        })
    }
}