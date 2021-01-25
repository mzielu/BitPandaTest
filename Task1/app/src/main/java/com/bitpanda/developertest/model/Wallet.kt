package com.bitpanda.developertest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallet(
    val id: String,
    val isDefault: Boolean,
    val balance: Double,
    val deleted: Boolean,
    val name: String,
    val resource: Resource
) : Parcelable