package com.bitpanda.developertest.ui.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.ItemFiatBinding

class FiatWalletHolder private constructor(val binding: ItemFiatBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(fiat: FiatWalletDisplayable, walletClickAction: () -> Unit) {
        binding.fiat = fiat
        binding.root.setOnClickListener {
            walletClickAction()
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): FiatWalletHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemFiatBinding.inflate(layoutInflater, parent, false)
            return FiatWalletHolder(binding)
        }
    }
}

data class FiatWalletDisplayable(
    val name: String,
    val balance: Double,
    val logo: String
)