package com.bitpanda.developertest.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

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
}