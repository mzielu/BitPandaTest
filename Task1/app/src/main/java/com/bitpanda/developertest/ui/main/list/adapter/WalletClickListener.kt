package com.bitpanda.developertest.ui.main.list.adapter

import com.bitpanda.developertest.model.Wallet

interface WalletClickListener {
    fun onItemClick(item: Wallet)
}