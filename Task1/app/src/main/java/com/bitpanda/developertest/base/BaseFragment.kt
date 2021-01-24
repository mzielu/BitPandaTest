package com.bitpanda.developertest.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {
    private val compositeDisposable = CompositeDisposable()

    lateinit var viewModel: VM
    var binding: VB? = null

    override fun onStart() {
        super.onStart()
        viewModel.start()
    }

    override fun onStop() {
        viewModel.stop()
        compositeDisposable.clear()
        super.onStop()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}