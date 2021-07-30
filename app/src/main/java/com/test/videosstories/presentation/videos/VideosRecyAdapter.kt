package com.test.videosstories.presentation.videos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.test.videosstories.common.model.ItemForView
import com.test.videosstories.common.view.GenericRecyListAdapter
import com.test.videosstories.common.view.IDiffItemCallback
import com.test.videosstories.common.view.ITextSearchFilter
import com.test.videosstories.databinding.VideoItemBinding
import com.test.videosstories.domain.Video

class VideosRecyAdapter(
    _context: Context,
    list: List<Video>?,
    filter: ITextSearchFilter<Video>?,
    diffCallback: IDiffItemCallback<Video>
) : GenericRecyListAdapter<Video>(_context, list, filter, diffCallback) {

    private lateinit var allVideosViewModel: AllVideosViewModel
    private lateinit var lifecycleOwner: LifecycleOwner

    constructor(
        context: Context, list: List<Video>?, filter: ITextSearchFilter<Video>?, diffCallback: IDiffItemCallback<Video>,
        videosViewModel: AllVideosViewModel, lifecycleOwner: LifecycleOwner
    ) : this(context, list, filter, diffCallback) {
        this.allVideosViewModel = videosViewModel
        this.lifecycleOwner = lifecycleOwner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: VideoItemBinding = VideoItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as MyViewHolder
        val element: Video = currentList[position]
        holder.bind(allVideosViewModel, element)
    }

    fun getItemPosition(item: ItemForView): Int {
        for (itemForView in currentList) {
            if (itemForView.id == item.id) {
                return currentList.indexOf(itemForView)
            }
        }
        return -1
    }

    inner class MyViewHolder(val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(allVideosViewModel: AllVideosViewModel, itemForView: Video) {
            binding.lifecycleOwner = lifecycleOwner
            binding.viewModel = allVideosViewModel
            val itemViewModel = VideoItemViewModel()
            itemViewModel.setItem(itemForView)
            binding.viewModelItem = itemViewModel
            binding.executePendingBindings()
        }
    }
}