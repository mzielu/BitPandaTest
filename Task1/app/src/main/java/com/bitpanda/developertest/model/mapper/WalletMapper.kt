package com.bitpanda.developertest.model.mapper

import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.model.dto.ResourceDto
import com.bitpanda.developertest.model.dto.WalletDto
import javax.inject.Inject

class WalletMapper @Inject constructor(
    private val resourceMapper: ResourceMapper
) {
    fun map(walletDto: WalletDto, resourceDto: ResourceDto, resourceType: ResourceType): Wallet {
        val resource = resourceMapper.map(resourceDto, resourceType)

        return with(walletDto) {
            Wallet(
                id = id,
                isDefault = isDefault,
                balance = balance,
                deleted = deleted,
                name = name,
                resource = resource
            )
        }
    }
}