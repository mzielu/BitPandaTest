package com.bitpanda.developertest.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bitpanda.developertest.R
import com.bitpanda.developertest.base.BaseActivity
import com.bitpanda.developertest.databinding.ActivityMainBinding
import com.bitpanda.developertest.service.NavigatorService
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var navigationDispatcher: NavigatorService

    private val mainViewModel: MainViewModel by viewModels()

    private val navController by lazy {
        Navigation.findNavController(this, R.id.navigation_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = mainViewModel

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        subscribe {
            navigationDispatcher.observeNavigationEmissions().subscribeBy { command ->
                command.invoke(navController)
            }
        }
    }

    override fun onBackPressed() {
        //ANDROID 10 leak bug fix
        if (isTaskRoot && supportFragmentManager.backStackEntryCount == 0) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}