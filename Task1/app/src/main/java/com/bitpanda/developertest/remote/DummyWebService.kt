package com.bitpanda.developertest.remote

import com.bitpanda.developertest.model.dto.ResourceDto
import com.bitpanda.developertest.model.dto.WalletDto
import com.bitpanda.developertest.remote.DummyData.Companion.cryptocoinWallets
import com.bitpanda.developertest.remote.DummyData.Companion.cryptocoins
import com.bitpanda.developertest.remote.DummyData.Companion.fiatWallets
import com.bitpanda.developertest.remote.DummyData.Companion.fiats
import com.bitpanda.developertest.remote.DummyData.Companion.metalWallets
import com.bitpanda.developertest.remote.DummyData.Companion.metals
import javax.inject.Inject

interface WebService {
    fun getCryptoWallets(): List<WalletDto>

    fun getMetalWallets(): List<WalletDto>

    fun getFiatWallets(): List<WalletDto>

    fun getCryptocoins(): List<ResourceDto>

    fun getMetals(): List<ResourceDto>

    fun getFiats(): List<ResourceDto>
}

class DummyWebService @Inject constructor() : WebService {
    override fun getCryptoWallets() = cryptocoinWallets

    override fun getMetalWallets() = metalWallets

    override fun getFiatWallets() = fiatWallets

    override fun getCryptocoins() = cryptocoins

    override fun getMetals() = metals

    override fun getFiats() = fiats
}