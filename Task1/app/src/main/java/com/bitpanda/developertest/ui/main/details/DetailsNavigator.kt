package com.bitpanda.developertest.ui.main.details

import com.bitpanda.developertest.service.NavigatorService
import javax.inject.Inject

class DetailsNavigator @Inject constructor(
    private val navigatorService: NavigatorService
) {
    fun goBack() {
        navigatorService.emit { navController ->
            navController.navigateUp()
        }
    }
}