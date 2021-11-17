package com.example.hilt.hilt

import com.example.hilt.network.CallInterceptorImpl
import com.example.hilt.network.NetworkService
import com.example.hilt.network.ResponseInterceptorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    // instantiate a specific class of the interface
    @CallInterceptor
    @Provides
    fun provideCallNetworkService(): NetworkService =
        NetworkService.Builder()
            .host("google.com")
            .protocol("HTTPS")
            .interceptor(CallInterceptorImpl())
            .build()

    @ResponseInterceptor
    @Provides
    fun provideResponseNetworkService(): NetworkService =
        NetworkService.Builder()
            .host("google.com")
            .protocol("HTTPS")
            .interceptor(ResponseInterceptorImpl())
            .build()
}