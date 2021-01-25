package com.bitpanda.developertest.ui.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bitpanda.developertest.R
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
        .setPlaceHolder(R.drawable.ic_asset_default, R.drawable.ic_asset_default)
        .load(Uri.parse(url), view)
}