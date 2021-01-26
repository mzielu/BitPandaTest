package com.bitpanda.developertest.ui.main.details

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.hilt.lifecycle.ViewModelInject
import com.bitpanda.developertest.base.BaseViewModel
import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.Wallet
import java.math.BigDecimal
import java.math.RoundingMode

class DetailsViewModel @ViewModelInject constructor(
    private val detailsNavigator: DetailsNavigator
) : BaseViewModel() {
    fun backPressed() {
        detailsNavigator.goBack()
    }

    fun convertForBalanceInEuro(wallet: Wallet): String {
        return with(wallet.resource) {
            price?.let {
                "${(it * wallet.balance).toString(precision)} €"
            } ?: ""
        }
    }

    fun convertForBalance(wallet: Wallet): String {
        return wallet.balance.toString(wallet.resource.precision)
    }

    fun balanceEuroVisible(wallet: Wallet): Int {
        return when (wallet.resource) {
            is Resource.Fiat -> GONE
            else -> VISIBLE
        }
    }

    private fun Double.toString(decimalNumbers: Int): String {
        return BigDecimal(this).setScale(decimalNumbers, RoundingMode.HALF_UP).stripTrailingZeros()
            .toPlainString()
    }
}