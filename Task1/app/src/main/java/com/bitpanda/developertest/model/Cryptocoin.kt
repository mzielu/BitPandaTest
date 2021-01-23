package com.bitpanda.developertest.model

data class Cryptocoin(
    var precision: Int = 4,
    var name: String = "",
    var symbol: String = "",
    var id: String = "",
    var price: Double = 0.0,
    var logo: String,
)