package com.bitpanda.developertest.ui.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.ItemCryptocoinBinding

class CryptocoinHolder private constructor(val binding: ItemCryptocoinBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cryptocoin: CryptocoinDisplayable) {
        binding.cryptocoin = cryptocoin
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CryptocoinHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCryptocoinBinding.inflate(layoutInflater, parent, false)
            return CryptocoinHolder(binding)
        }
    }
}

data class CryptocoinDisplayable(
    val name: String,
    val symbol: String,
    val balance: String,
    val balanceInEuro: String,
    val logo: String
)