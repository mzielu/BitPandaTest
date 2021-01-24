package com.bitpanda.developertest.remote

import com.bitpanda.developertest.model.dto.ResourceDto
import com.bitpanda.developertest.model.dto.WalletDto

class DummyData {
    companion object {
        val fiats = listOf(
            ResourceDto(
                name = "Euro",
                symbol = "EUR",
                id = "1",
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/usd.svg"
            ),
            ResourceDto(
                name = "Swiss Franc",
                symbol = "CHF",
                id = "2",
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/chf.svg"
            )
        )

        val metals = listOf(
            ResourceDto(
                name = "Gold",
                symbol = "XAU",
                id = "4",
                price = 45.14,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xau.svg"
            ),
            ResourceDto(
                name = "Palladium",
                symbol = "XPD",
                id = "5",
                price = 70.40,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xpd.svg"
            )
        )

        val cryptocoins = listOf(
            ResourceDto(
                name = "Bitcoin",
                symbol = "BTC",
                id = "1",
                price = 9000.0,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/btc.svg"
            ),
            ResourceDto(
                name = "Bitpanda Ecosystem Token",
                symbol = "BEST",
                id = "2",
                price = 0.03,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/best.svg"
            ),
            ResourceDto(
                name = "Ripple",
                symbol = "XRP",
                id = "3",
                price = 0.2119,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xrp.svg"
            )
        )

        val fiatWallets = listOf(
            WalletDto(
                id = "1",
                name = "EUR Wallet",
                balance = 400.0,
                isDefault = false,
                deleted = false,
                resourceId = "1",
            ),
            WalletDto(
                id = "2",
                name = "CHF Wallet",
                balance = 0.0,
                isDefault = false,
                deleted = false,
                resourceId = "2"
            )
        )

        val cryptocoinWallets = listOf(
            WalletDto(
                id = "1",
                name = "Test BTC Wallet",
                balance = 133.7,
                isDefault = false,
                deleted = false,
                resourceId = "1"
            ),
            WalletDto(
                id = "2",
                name = "Test BTC Wallet 2",
                balance = 0.0,
                isDefault = false,
                resourceId = "1",
                deleted = true
            ),
            WalletDto(
                id = "3",
                name = "Test BEST Wallet",
                balance = 1032.23,
                isDefault = false,
                deleted = false,
                resourceId = "2"
            ),
            WalletDto(
                id = "4",
                name = "Test Ripple Wallet",
                balance = 2304.04,
                isDefault = false,
                deleted = false,
                resourceId = "3"
            )
        )

        val metalWallets = listOf(
            WalletDto(
                id = "1",
                name = "Gold Wallet 1",
                balance = 133.729,
                isDefault = true,
                deleted = false,
                resourceId = "4"
            ),
            WalletDto(
                id = "2",
                name = "Gold Wallet 2",
                balance = 2043.4340,
                isDefault = false,
                deleted = false,
                resourceId = "4"
            ),
            WalletDto(
                id = "2",
                name = "Test Palladium Wallet",
                balance = 200.0,
                isDefault = false,
                deleted = false,
                resourceId = "5"
            )
        )
    }
}