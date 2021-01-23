package com.bitpanda.developertest.model

class FiatWallet(

    var id: String = "",
    var fiat_id: String = "",
    var is_default: Boolean = false,
    var balance: Double = 0.0,
    var deleted: Boolean = false,
    var name: String = "",
) {

    //todo implement me
    fun reduceBalance(amount: Double) {
    }

    //todo implement me
    fun addBalance(amount: Double) {
    }

}