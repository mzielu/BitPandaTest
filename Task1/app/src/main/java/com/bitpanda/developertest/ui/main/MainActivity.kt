package com.bitpanda.developertest.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.bitpanda.developertest.R
import com.bitpanda.developertest.base.BaseActivity
import com.bitpanda.developertest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = mainViewModel

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
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