package com.bitpanda.developertest.ui.main.list

import com.bitpanda.developertest.R
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.service.NavigatorService
import javax.inject.Inject

class ListNavigator @Inject constructor(
    private val navigatorService: NavigatorService
) {
    fun goToWalletDetails(wallet: Wallet) {
        navigatorService.emit { navController ->
            navController.navigate(R.id.action_nav_list_to_nav_details)
        }
    }
}