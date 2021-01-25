package com.bitpanda.developertest.ui.widgets

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bitpanda.developertest.R
import com.bitpanda.developertest.model.FilterType
import kotlinx.android.synthetic.main.view_filter_types.view.*
import timber.log.Timber

typealias TypeWithView = Pair<FilterType, View>

class FilterTypesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var typesWithViews: MutableList<TypeWithView> = mutableListOf()
    private var selectedType: FilterType? = null

    private var filterChangeAction: (FilterType) -> Unit = { }

    init {
        initView()
    }

    fun onFilterChangeAction(action: (FilterType) -> Unit) {
        filterChangeAction = action
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.view_filter_types, null)
        addView(view)

        typesWithViews.apply {
            add(FilterType.ALL to allView)
            add(FilterType.METAL to metalView)
            add(FilterType.CRYPTO to cryptoView)
            add(FilterType.FIAT to fiatView)
        }

        typesWithViews.forEach {
            it.second.configureFilterItemClick(it.first)
        }
    }

    private fun View.configureFilterItemClick(filterType: FilterType) {
        setOnClickListener { clickedView ->
            if (selectedType != null && selectedType == filterType) {
                Timber.d("Item already selected")
            } else {
                Timber.d("Selecting new item")
                selectedType = filterType
                clickedView.selectViewAndUnselectOthers()
                filterChangeAction(filterType)
            }
        }
    }

    private fun View.selectViewAndUnselectOthers() {
        isSelected = true
        typesWithViews.filter { it.first != selectedType }.forEach { item ->
            item.second.isSelected = false
        }
    }

    private fun getViewByType(filterType: FilterType): View? {
        return typesWithViews.firstOrNull { it.first == filterType }?.second
    }

    public override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        return if (superState != null) {
            selectedType?.let { SavedState(superState, it) } ?: superState
        } else {
            superState
        }
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            getViewByType(state.filterType)?.selectViewAndUnselectOthers()
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    class SavedState(superState: Parcelable, val filterType: FilterType) :
        BaseSavedState(superState) {
        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeSerializable(filterType)
        }
    }
}