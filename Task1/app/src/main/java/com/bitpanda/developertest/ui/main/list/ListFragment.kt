package com.bitpanda.developertest.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitpanda.developertest.base.BaseFragment
import com.bitpanda.developertest.databinding.FragmentListBinding
import com.bitpanda.developertest.model.mapper.ResourceMapper
import com.bitpanda.developertest.model.mapper.WalletMapper
import com.bitpanda.developertest.remote.DummyWebService
import com.bitpanda.developertest.repository.Repository
import com.bitpanda.developertest.repository.RepositoryImpl
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
        val adapter = WalletsAdapter()

        binding?.walletsView?.adapter = adapter
        viewModel.wallets.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
