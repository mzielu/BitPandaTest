package com.bitpanda.developertest.di

import com.bitpanda.developertest.remote.DummyWebService
import com.bitpanda.developertest.remote.WebService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class ServiceModule {

    @Binds
    @Singleton
    abstract fun bindWebService(impl: DummyWebService): WebService
}