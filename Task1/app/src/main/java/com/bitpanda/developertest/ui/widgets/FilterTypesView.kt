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


class FilterTypesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var filterViews: MutableList<View> = mutableListOf()
    private lateinit var selectedView: View

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

        filterViews.addAll(listOf(allView, metalView, cryptoView, fiatView))

        allView.configureFilterItemClick(FilterType.ALL)
        metalView.configureFilterItemClick(FilterType.METAL)
        cryptoView.configureFilterItemClick(FilterType.CRYPTO)
        fiatView.configureFilterItemClick(FilterType.FIAT)
    }

    private fun View.configureFilterItemClick(filterType: FilterType) {
        setOnClickListener { clickedView ->
            if (::selectedView.isInitialized && selectedView == clickedView) {
                Timber.d("Item already selected")
            } else {
                Timber.d("Selecting new item")
                clickedView.selectView()
                filterChangeAction(filterType)
            }
        }
    }

    private fun View.selectView() {
        selectedView = this
        isSelected = true
        filterViews.filter { it != this }.forEach { iteratedView ->
            iteratedView.isSelected = false
        }
    }

    public override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        return if (superState != null) {
            val savedState = SavedState(superState, selectedView.id)
            savedState
        } else {
            superState
        }
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            findViewById<View>(state.selectedViewId).selectView()
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    class SavedState(superState: Parcelable, val selectedViewId: Int) : BaseSavedState(superState) {
        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(selectedViewId)
        }
    }
}