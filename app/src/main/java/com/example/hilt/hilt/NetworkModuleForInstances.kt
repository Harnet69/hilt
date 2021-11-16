package com.example.hilt.hilt

import com.example.hilt.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
// we can provide in which class we want inject, we add network module to component
@InstallIn(ActivityComponent::class)
class NetworkModuleForInstances {

    @Provides
    fun provideNetworkService(): NetworkService =
        NetworkService.Builder()
            .host("google.com")
            .protocol("HTTPS")
            .build()
}