package com.bitpanda.developertest.repository

import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.model.dto.ResourceDto
import com.bitpanda.developertest.model.dto.WalletDto
import com.bitpanda.developertest.model.mapper.WalletMapper
import com.bitpanda.developertest.remote.DummyWebService
import javax.inject.Inject

interface Repository {
    fun getWallets(vararg types: ResourceType): List<Wallet>
}

class RepositoryImpl @Inject constructor(
    private val webservice: DummyWebService,
    private val walletMapper: WalletMapper
) : Repository {

    override fun getWallets(vararg types: ResourceType): List<Wallet> {
        val wallets = mutableListOf<Wallet>()

        if (types.contains(ResourceType.FIAT)) {
            wallets.addAll(getFiatWallets())
        }

        if (types.contains(ResourceType.CRYPTOCOIN)) {
            wallets.addAll(getCryptoWallets())
        }

        if (types.contains(ResourceType.METAL)) {
            wallets.addAll(getMetalWallets())
        }

        return wallets
    }

    private fun getCryptoWallets(): List<Wallet> {
        return webservice.getCryptoWallets()
            .mapToWalletsSorted(webservice.getCryptocoins(), ResourceType.CRYPTOCOIN)
    }

    private fun getMetalWallets(): List<Wallet> {
        return webservice.getMetalWallets()
            .mapToWalletsSorted(webservice.getMetals(), ResourceType.METAL)
    }

    private fun getFiatWallets(): List<Wallet> {
        return webservice.getFiatWallets()
            .mapToWalletsSorted(webservice.getFiats(), ResourceType.FIAT)
    }

    private fun List<WalletDto>.mapToWalletsSorted(
        availableResources: List<ResourceDto>,
        resourceType: ResourceType
    ): List<Wallet> {
        val wallets = mutableListOf<Wallet>()

        forEach { walletDto ->
            val desiredResource = availableResources.find { resource ->
                walletDto.resourceId == resource.id
            }

            desiredResource?.let { resourceDto ->
                wallets.add(walletMapper.map(walletDto, resourceDto, resourceType))
            }
        }

        return wallets.sortedByDescending { it.balance }
    }
}