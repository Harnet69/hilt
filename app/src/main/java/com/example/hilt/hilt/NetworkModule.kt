package com.example.hilt.hilt

import com.example.hilt.network.CallInterceptorImpl
import com.example.hilt.network.NetworkService
import com.example.hilt.network.ResponseInterceptorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
// instantiate a specific class of the interface
@InstallIn(ActivityComponent::class)
class NetworkModule {

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