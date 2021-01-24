package com.bitpanda.developertest.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.CryptoItemBinding
import com.bitpanda.developertest.databinding.FiatItemBinding
import com.bitpanda.developertest.databinding.MetalItemBinding
import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.Wallet
import java.lang.IllegalArgumentException

class WalletsAdapter : ListAdapter<Wallet, RecyclerView.ViewHolder>(WalletDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position).resource) {
            is Resource.Metal -> (holder as MetalHolder).bind(item)
            is Resource.Cryptocoin -> (holder as CryptoHolder).bind(item)
            is Resource.Fiat -> (holder as FiatHolder).bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ONE -> MetalHolder.from(parent)
            VIEW_TYPE_TWO -> CryptoHolder.from(parent)
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

    class MetalHolder private constructor(val binding: MetalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Resource.Metal) {
            binding.metal = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MetalHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MetalItemBinding.inflate(layoutInflater, parent, false)
                return MetalHolder(binding)
            }
        }
    }

    class CryptoHolder private constructor(val binding: CryptoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Resource.Cryptocoin) {
            binding.cryptocoin = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CryptoHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CryptoItemBinding.inflate(layoutInflater, parent, false)
                return CryptoHolder(binding)
            }
        }
    }

    class FiatHolder private constructor(val binding: FiatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Resource.Fiat) {
            binding.fiat = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FiatHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FiatItemBinding.inflate(layoutInflater, parent, false)
                return FiatHolder(binding)
            }
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