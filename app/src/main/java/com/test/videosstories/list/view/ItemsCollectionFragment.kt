package com.test.videosstories.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.videosstories.databinding.FragmentItemsCollectionBinding
import com.test.videosstories.list.viewmodel.ItemsCollectionViewModel

class ItemsCollectionFragment : Fragment() {
    val LOG_TAG = ItemsCollectionFragment::class.simpleName

    private lateinit var dataBinding: FragmentItemsCollectionBinding

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

        activity?.setTitle("Items collection")
    }
}