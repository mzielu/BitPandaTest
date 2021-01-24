package com.bitpanda.developertest.model.dto

data class ResourceDto(
    val id: String,
    val name: String,
    val symbol: String,
    val logo: String,
    val price: Double? = null,
) 