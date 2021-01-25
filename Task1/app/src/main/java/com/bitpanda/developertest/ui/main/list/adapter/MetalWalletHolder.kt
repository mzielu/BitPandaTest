package com.bitpanda.developertest.ui.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.ItemMetalBinding

class MetalWalletHolder private constructor(val binding: ItemMetalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(metal: MetalWalletDisplayable, walletClickAction: () -> Unit) {
        binding.metal = metal
        binding.root.setOnClickListener {
            walletClickAction()
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MetalWalletHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMetalBinding.inflate(layoutInflater, parent, false)
            return MetalWalletHolder(binding)
        }
    }
}

data class MetalWalletDisplayable(
    val resourceName: String,
    val balance: Double,
    val logo: String
)