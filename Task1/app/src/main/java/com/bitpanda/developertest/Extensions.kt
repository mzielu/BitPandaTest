package com.bitpanda.developertest

import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Bundle.getParcelableOrNull(key: String): T? {
    return getParcelable(key) as? T
}

