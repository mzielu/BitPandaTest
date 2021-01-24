package com.bitpanda.developertest.model

data class Wallet(
    val id: String,
    val isDefault: Boolean,
    val balance: Double,
    val deleted: Boolean,
    val name: String,
    val resource: Resource
)