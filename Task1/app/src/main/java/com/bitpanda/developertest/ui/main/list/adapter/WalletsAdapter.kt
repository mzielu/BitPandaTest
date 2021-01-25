package com.bitpanda.developertest.ui.main.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.Wallet

class WalletsAdapter : ListAdapter<Wallet, RecyclerView.ViewHolder>(WalletDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.resource) {
            is Resource.Metal -> (holder as MetalHolder).bind(
                MetalDisplayable(
                    resourceName = item.resource.name,
                    balance = item.balance,
                    logo = item.resource.logo
                )
            )
            is Resource.Cryptocoin -> (holder as CryptocoinHolder).bind(
                CryptocoinDisplayable(
                    name = item.name,
                    balance = item.balance.toString(),
                    symbol = item.resource.symbol,
                    balanceInEuro = (item.balance * item.resource.price).toString(),
                    logo = item.resource.logo
                )
            )
            is Resource.Fiat -> (holder as FiatHolder).bind(
                FiatDisplayable(
                    name = item.name,
                    balance = item.balance,
                    logo = item.resource.logo
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ONE -> MetalHolder.from(parent)
            VIEW_TYPE_TWO -> CryptocoinHolder.from(parent)
            VIEW_TYPE_THREE -> FiatHolder.from(parent)
            else -> throw IllegalArgumentException("Unknown view type!")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).resource) {
            is Resource.Metal -> VIEW_TYPE_ONE
            is Resource.Cryptocoin -> VIEW_TYPE_TWO
            is Resource.Fiat -> VIEW_TYPE_THREE
        }
    }

    companion object {
        private const val VIEW_TYPE_ONE = 1
        private const val VIEW_TYPE_TWO = 2
        private const val VIEW_TYPE_THREE = 3
    }
}

class WalletDiffCallback : DiffUtil.ItemCallback<Wallet>() {
    override fun areItemsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
        return oldItem.id == newItem.id && oldItem.resource == newItem.resource
    }

    override fun areContentsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
        return oldItem.id == newItem.id && oldItem.resource == newItem.resource
    }
}