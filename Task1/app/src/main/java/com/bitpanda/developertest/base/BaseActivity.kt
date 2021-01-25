package com.bitpanda.developertest.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()

    lateinit var viewModel: VM
    lateinit var binding: VB

    override fun onStart() {
        super.onStart()
        viewModel.start()
    }

    override fun onStop() {
        compositeDisposable.clear()
        viewModel.stop()
        super.onStop()
    }

    inline infix fun BaseActivity<VM, VB>.subscribe(crossinline block: () -> Disposable) {
        compositeDisposable.add(block())
    }
}