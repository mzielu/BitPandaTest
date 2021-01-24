package com.bitpanda.developertest.ui.main.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitpanda.developertest.base.BaseViewModel
import com.bitpanda.developertest.model.FilterType
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.repository.Repository
import timber.log.Timber

class ListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private var walletsListMutable = MutableLiveData<List<Wallet>>(emptyList())
    val wallets: LiveData<List<Wallet>>
        get() = walletsListMutable

    val filterChangeAction: (FilterType) -> Unit = { filterType ->
        Timber.d("New filter: $filterType")

        when (filterType) {
            FilterType.ALL -> {
                walletsListMutable.value = repository.getWallets(*ResourceType.values())
            }
            FilterType.METAL -> {
                walletsListMutable.value = repository.getWallets(ResourceType.METAL)
            }
            FilterType.FIAT -> {
                walletsListMutable.value = repository.getWallets(ResourceType.FIAT)
            }
            FilterType.CRYPTO -> {
                walletsListMutable.value = repository.getWallets(ResourceType.CRYPTOCOIN)
            }
        }
    }
}