package com.bitpanda.developertest.ui.binding

import android.R
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bitpanda.developertest.model.FilterType
import com.bitpanda.developertest.ui.widgets.FilterTypesView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@BindingAdapter("filterChangeAction")
fun setLineClickAction(view: FilterTypesView, action: (FilterType) -> Unit) {
    view.onFilterChangeAction {
        action(it)
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    GlideToVectorYou
        .init()
        .with(view.context)
        .load(Uri.parse(url), view)
}