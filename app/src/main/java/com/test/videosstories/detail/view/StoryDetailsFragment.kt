package com.test.videosstories.detail.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.videosstories.MainActivity
import com.test.videosstories.databinding.FragmentStoryDetailsBinding
import com.test.videosstories.detail.viewmodel.StoryDetailsViewModel
import com.test.videosstories.detail.viewmodel.StoryDetailsViewModelFactory
import javax.inject.Inject

class StoryDetailsFragment : Fragment() {
    val LOG_TAG = StoryDetailsFragment::class.simpleName

    private lateinit var dataBinding: FragmentStoryDetailsBinding

    @Inject
    lateinit var viewModelFactory: StoryDetailsViewModelFactory
    private lateinit var viewModel: StoryDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentStoryDetailsBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(this, viewModelFactory).get(StoryDetailsViewModel::class.java)

        dataBinding.viewModelItem = viewModel
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

        viewModel.setStoryId(StoryDetailsFragmentArgs.fromBundle(requireArguments()).itemId)

        viewModel.backToHome.observe(viewLifecycleOwner, {
            if (it == true) {
                activity?.onBackPressed()
                viewModel.doneBackToHome()
            }
        })
    }

}