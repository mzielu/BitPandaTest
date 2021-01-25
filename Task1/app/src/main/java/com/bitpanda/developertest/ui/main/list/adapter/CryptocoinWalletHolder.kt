package com.bitpanda.developertest.ui.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.ItemCryptocoinBinding

class CryptocoinWalletHolder private constructor(val binding: ItemCryptocoinBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cryptocoin: CryptocoinWalletDisplayable, walletClickAction: () -> Unit) {
        binding.cryptocoin = cryptocoin
        binding.root.setOnClickListener {
            walletClickAction()
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CryptocoinWalletHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCryptocoinBinding.inflate(layoutInflater, parent, false)
            return CryptocoinWalletHolder(binding)
        }
    }
}

data class CryptocoinWalletDisplayable(
    val name: String,
    val symbol: String,
    val balance: String,
    val balanceInEuro: String,
    val logo: String
)