package com.bitpanda.developertest.ui.binding

import androidx.databinding.BindingAdapter
import com.bitpanda.developertest.model.FilterType
import com.bitpanda.developertest.ui.widgets.FilterTypesView

@BindingAdapter("filterChangeAction")
fun setLineClickAction(view: FilterTypesView, action: (FilterType) -> Unit) {
    view.onFilterChangeAction {
        action(it)
    }
}