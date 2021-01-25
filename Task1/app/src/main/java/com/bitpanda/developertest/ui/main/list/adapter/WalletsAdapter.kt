package com.bitpanda.developertest.ui.main.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.Wallet

class WalletsAdapter(private val walletClickAction: (Wallet) -> Unit) :
    ListAdapter<Wallet, RecyclerView.ViewHolder>(WalletDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.resource) {
            is Resource.Metal -> (holder as MetalWalletHolder).bind(
                MetalWalletDisplayable(
                    resourceName = item.resource.name,
                    balance = item.balance,
                    logo = item.resource.logo
                )
            ) { walletClickAction(item) }
            is Resource.Cryptocoin -> (holder as CryptocoinWalletHolder).bind(
                CryptocoinWalletDisplayable(
                    name = item.name,
                    balance = item.balance.toString(),
                    symbol = item.resource.symbol,
                    balanceInEuro = (item.balance * item.resource.price).toString(),
                    logo = item.resource.logo
                )
            ) { walletClickAction(item) }
            is Resource.Fiat -> (holder as FiatWalletHolder).bind(
                FiatWalletDisplayable(
                    name = item.name,
                    balance = item.balance,
                    logo = item.resource.logo
                )
            ) { walletClickAction(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ONE -> MetalWalletHolder.from(parent)
            VIEW_TYPE_TWO -> CryptocoinWalletHolder.from(parent)
            VIEW_TYPE_THREE -> FiatWalletHolder.from(parent)
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