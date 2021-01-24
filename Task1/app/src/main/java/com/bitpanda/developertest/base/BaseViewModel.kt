package com.bitpanda.developertest.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    open fun start() {
    }

    open fun stop() {
        compositeDisposable.clear()
    }

    inline infix fun BaseViewModel.subscribe(crossinline block: () -> Disposable) {
        compositeDisposable.add(block())
    }
}