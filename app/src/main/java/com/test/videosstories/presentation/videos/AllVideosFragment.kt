package com.test.videosstories.presentation.videos

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.videosstories.common.util.sortVideosByDate
import com.test.videosstories.common.view.IDiffItemCallback
import com.test.videosstories.common.view.ITextSearchFilter
import com.test.videosstories.databinding.CustomActionbarBinding
import com.test.videosstories.databinding.FragmentAllVideosBinding
import com.test.videosstories.domain.Video
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllVideosFragment : Fragment() {
    val LOG_TAG = AllVideosFragment::class.simpleName

    private lateinit var dataBinding: FragmentAllVideosBinding
    private lateinit var adapter: VideosRecyAdapter

    private val viewModel: AllVideosViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentAllVideosBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.show()
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        val actionbarBinding = CustomActionbarBinding.inflate(layoutInflater)
        actionbarBinding.tvTitle.text = "All Videos"
        actionBar?.customView = actionbarBinding.root

        val window = activity?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //window?.insetsController?.show(WindowInsets.Type.statusBars())
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        adapter = VideosRecyAdapter(requireContext(), viewModel.allVideos.value, object : ITextSearchFilter<Video> {
            override fun shouldBeDisplayed(constraint: CharSequence?, obj: Video): Boolean {
                if (constraint == null || constraint.isEmpty()) return true
                val title = obj.title
                if (title.isEmpty()) return true

                val query = constraint.toString().toLowerCase()
                return (title.toLowerCase().contains(query))
            }
        }, object : IDiffItemCallback<Video> {
            override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem == newItem
            }
        }, viewModel, viewLifecycleOwner)

        val recyclerView = dataBinding.recyAllItems
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        viewModel.allVideos.observe(viewLifecycleOwner, { allItems ->
            adapter.submitList(sortVideosByDate(allItems))
        })

        viewModel.clickedItem.observe(viewLifecycleOwner, {
            it?.apply {
                /*if (it.isVideo) {
                    val action = ItemsCollectionFragmentDirections.actionItemsCollectionToPlayerVideo()
                    action.urlVideo = it.url.toString()
                    findNavController().navigate(action)
                    viewModel.doneNavigating()
                } else {
                    val action = ItemsCollectionFragmentDirections.actionItemsCollectionToStoryDetails()
                    action.itemId = it.id
                    findNavController().navigate(action)
                    viewModel.doneNavigating()
                }*/
            }
        })

        /*viewModel.needNotifyNetworkError.observe(viewLifecycleOwner, {
            if (it == true) {
                Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
                viewModel.doneNotifyNetworkError()
            }
        })*/
    }
}