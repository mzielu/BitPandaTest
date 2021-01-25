package com.bitpanda.developertest.ui.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.ItemMetalBinding

class MetalHolder private constructor(val binding: ItemMetalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(metalDisplayable: MetalDisplayable) {
        binding.metal = metalDisplayable
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MetalHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMetalBinding.inflate(layoutInflater, parent, false)
            return MetalHolder(binding)
        }
    }
}

data class MetalDisplayable(
    val resourceName: String,
    val balance: Double,
    val logo: String
)