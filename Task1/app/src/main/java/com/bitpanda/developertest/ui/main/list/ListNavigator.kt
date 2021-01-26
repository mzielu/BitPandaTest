package com.bitpanda.developertest.ui.main.list

import android.os.Bundle
import com.bitpanda.developertest.R
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.service.NavigatorService
import com.bitpanda.developertest.ui.main.details.DetailsFragment.Companion.EXTRA_WALLET_KEY
import javax.inject.Inject

class ListNavigator @Inject constructor(
    private val navigatorService: NavigatorService
) {
    fun goToWalletDetails(wallet: Wallet) {
        navigatorService.emit { navController ->
            navController.navigate(R.id.action_nav_list_to_nav_details, Bundle().apply {
                putParcelable(EXTRA_WALLET_KEY, wallet)
            })
        }
    }
}