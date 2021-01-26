package com.bitpanda.developertest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class Resource(
    open val id: String,
    open val name: String,
    open val symbol: String,
    open val logo: String,
    open val precision: Int,
    open val price: Double? = null
) : Parcelable {
    @Parcelize
    data class Fiat(
        override val id: String,
        override val name: String,
        override val symbol: String,
        override val logo: String,
        override val precision: Int = DEFAULT_FIAT_PRECISION
    ) : Resource(id, name, symbol, logo, precision, null)

    @Parcelize
    data class Metal(
        override val id: String,
        override val name: String,
        override val symbol: String,
        override val logo: String,
        override val precision: Int = DEFAULT_METAL_PRECISION,
        override val price: Double
    ) : Resource(id, name, symbol, logo, precision, price)

    @Parcelize
    data class Cryptocoin(
        override val id: String,
        override val name: String,
        override val symbol: String,
        override val logo: String,
        override val precision: Int = DEFAULT_CRYPTOCOIN_PRECISION,
        override val price: Double
    ) : Resource(id, name, symbol, logo, precision, price)

    companion object {
        private const val DEFAULT_FIAT_PRECISION = 2
        private const val DEFAULT_METAL_PRECISION = 3
        private const val DEFAULT_CRYPTOCOIN_PRECISION = 4
    }
}