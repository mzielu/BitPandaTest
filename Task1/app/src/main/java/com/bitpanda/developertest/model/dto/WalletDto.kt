package com.bitpanda.developertest.model.dto

data class WalletDto(
    val id: String,
    val isDefault: Boolean,
    val balance: Double,
    val deleted: Boolean,
    val name: String,
    val resourceId: String
)