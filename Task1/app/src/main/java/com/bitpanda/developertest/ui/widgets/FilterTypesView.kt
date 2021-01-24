package com.bitpanda.developertest.ui.widgets

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bitpanda.developertest.R
import kotlinx.android.synthetic.main.view_filter_types.view.*
import timber.log.Timber


class FilterTypesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var filterViews: MutableList<View> = mutableListOf()
    private lateinit var selectedView: View

    private var filterChangeAction: (FilterTypes) -> Unit = { }

    init {
        initView()
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.view_filter_types, null)
        addView(view)

        filterViews.addAll(listOf(allView, metalView, cryptoView, fiatView))

        allView.configureFilterItemClick(FilterTypes.ALL)
        metalView.configureFilterItemClick(FilterTypes.METAL)
        cryptoView.configureFilterItemClick(FilterTypes.CRYPTO)
        fiatView.configureFilterItemClick(FilterTypes.FIAT)
    }

    fun onFilterChangeAction(action: (FilterTypes) -> Unit) {
        filterChangeAction = action
    }

    private fun View.configureFilterItemClick(filterTypes: FilterTypes) {
        setOnClickListener { clickedView ->
            if (::selectedView.isInitialized && selectedView == clickedView) {
                Timber.d("Item already selected")
            } else {
                clickedView.selectView()
                filterChangeAction(filterTypes)
            }
        }
    }

    private fun View.selectView() {
        selectedView = this
        this.isSelected = true
        filterViews.filter { it != this }.forEach { iteratedView ->
            iteratedView.isSelected = false
        }
    }

    public override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()

        return if (superState != null) {
            val savedState = SavedState(superState)
            savedState.index = selectedView.id
            savedState
        } else {
            superState
        }
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            findViewById<View>(state.index).selectView()
        } else {
            super.onRestoreInstanceState(state)
            Timber.d("XDDDDDD 21321")
        }
    }

    internal class SavedState(superState: Parcelable) : BaseSavedState(superState) {
        var index = 0

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(index)
        }
    }
}

enum class FilterTypes {
    ALL, CRYPTO, FIAT, METAL
}