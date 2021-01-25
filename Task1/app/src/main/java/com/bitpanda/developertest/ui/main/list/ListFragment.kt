package com.bitpanda.developertest.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bitpanda.developertest.base.BaseFragment
import com.bitpanda.developertest.databinding.FragmentListBinding
import com.bitpanda.developertest.ui.main.list.adapter.WalletsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<ListViewModel, FragmentListBinding>() {

    private val profileViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = profileViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding?.viewModel = profileViewModel

        prepareAdapter()

        binding?.lifecycleOwner = this
        return binding?.root
    }

    private fun prepareAdapter() {
        val adapter = WalletsAdapter(viewModel.walletClickAction)
        binding?.walletsView?.adapter = adapter
        viewModel.wallets.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
