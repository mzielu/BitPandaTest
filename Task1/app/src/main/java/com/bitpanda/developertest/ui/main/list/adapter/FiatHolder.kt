package com.bitpanda.developertest.ui.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.ItemFiatBinding

class FiatHolder private constructor(val binding: ItemFiatBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(fiat: FiatDisplayable) {
        binding.fiat = fiat
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): FiatHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemFiatBinding.inflate(layoutInflater, parent, false)
            return FiatHolder(binding)
        }
    }
}

data class FiatDisplayable(
    val name: String,
    val balance: Double,
    val logo: String
)