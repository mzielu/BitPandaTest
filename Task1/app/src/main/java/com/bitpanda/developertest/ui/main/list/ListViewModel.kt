package com.bitpanda.developertest.ui.main.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitpanda.developertest.base.BaseViewModel
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.repository.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private var walletsListMutable = MutableLiveData<List<Wallet>>(emptyList())
    val wallets: LiveData<List<Wallet>>
        get() = walletsListMutable

    override fun start() {
        super.start()

        subscribe {
            Observable.just(Unit).delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                    onNext = {
                        val xd = repository.getWallets(
                            ResourceType.FIAT,
                            ResourceType.METAL,
                            ResourceType.CRYPTOCOIN
                        )

                        walletsListMutable.value = xd
                    }
                )
        }
    }
}