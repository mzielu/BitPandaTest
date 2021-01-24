package com.bitpanda.developertest.di

import com.bitpanda.developertest.remote.DummyWebService
import com.bitpanda.developertest.remote.WebService
import com.bitpanda.developertest.repository.Repository
import com.bitpanda.developertest.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWebService(impl: DummyWebService): WebService

    @Binds
    @Singleton
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}