package com.bitpanda.developertest.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.bitpanda.developertest.base.BaseFragment
import com.bitpanda.developertest.databinding.FragmentDetailsBinding
import com.bitpanda.developertest.getParcelableOrNull
import com.bitpanda.developertest.model.Wallet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {

    private val detailsViewModel: DetailsViewModel by viewModels()

    private var backPressedCallback: OnBackPressedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = detailsViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding?.viewModel = detailsViewModel
        binding?.wallet = retrieveWallet()
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.backPressed()
        }
    }

    override fun onPause() {
        backPressedCallback?.remove()
        backPressedCallback = null
        super.onPause()
    }

    private fun retrieveWallet(): Wallet {
        return arguments?.getParcelableOrNull(EXTRA_WALLET_KEY)
            ?: throw IllegalAccessException("Cannot start DetailsFragment without the wallet!")
    }

    companion object {
        const val EXTRA_WALLET_KEY = "extra_wallet_key"
    }
}
