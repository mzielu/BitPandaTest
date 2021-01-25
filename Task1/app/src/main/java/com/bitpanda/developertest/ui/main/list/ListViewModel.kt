package com.bitpanda.developertest.ui.main.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitpanda.developertest.base.BaseViewModel
import com.bitpanda.developertest.model.FilterType
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.repository.Repository

class ListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private var walletsListMutable = MutableLiveData<List<Wallet>>(emptyList())
    val wallets: LiveData<List<Wallet>>
        get() = walletsListMutable

    val filterChangeAction: (FilterType) -> Unit = { filterType ->
        walletsListMutable.value = when (filterType) {
            FilterType.ALL -> getNotDeletedWallets(*ResourceType.values())
            FilterType.METAL -> getNotDeletedWallets(ResourceType.METAL)
            FilterType.FIAT -> getNotDeletedWallets(ResourceType.FIAT)
            FilterType.CRYPTO -> getNotDeletedWallets(ResourceType.CRYPTOCOIN)
        }
    }

    private fun getNotDeletedWallets(vararg types: ResourceType): List<Wallet> {
        return repository.getWallets(*types).filter { !it.deleted }
    }
}