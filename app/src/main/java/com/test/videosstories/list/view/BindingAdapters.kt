package com.test.videosstories.list.view

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.videosstories.R
import com.test.videosstories.list.model.ItemForView
import org.apache.commons.lang3.StringUtils

@BindingAdapter("url")
fun bindImage(imgView: ImageView, item: ItemForView?) {
    if (item == null) return

    val imgUrl = if (item.isVideo) item.thumb else item.image
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).apply(
            RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
        ).into(imgView)
    }
}

@BindingAdapter("content")
fun setContentOfTextView(view: TextView, item: ItemForView?) {
    if (item == null) return

    if (item.isVideo) {
        view.text = item.views.toString() + StringUtils.SPACE + "views"
    } else {
        view.text = "By" + StringUtils.SPACE + item.author
    }

}