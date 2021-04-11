package com.test.videosstories.playvideo.view

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.videosstories.databinding.FragmentPlayerVideoBinding
import com.test.videosstories.databinding.FragmentStoryDetailsBinding
import com.test.videosstories.detail.viewmodel.StoryDetailsViewModel
import com.test.videosstories.detail.viewmodel.StoryDetailsViewModelFactory
import com.test.videosstories.playvideo.viewmodel.PlayerVideoViewModel
import com.test.videosstories.playvideo.viewmodel.PlayerVideoViewModelFactory

class PlayerVideoFragment : Fragment() {
    val LOG_TAG = PlayerVideoFragment::class.simpleName

    private lateinit var dataBinding: FragmentPlayerVideoBinding
    private lateinit var viewModel: PlayerVideoViewModel
    private lateinit var viewModelFactory: PlayerVideoViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentPlayerVideoBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        viewModelFactory = PlayerVideoViewModelFactory(PlayerVideoFragmentArgs.fromBundle(requireArguments()).urlVideo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayerVideoViewModel::class.java)

        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        val window = activity?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        viewModel.backToHome.observe(viewLifecycleOwner, {
            if (it == true) {
                activity?.onBackPressed()
                viewModel.doneBackToHome()
            }
        })
    }

}