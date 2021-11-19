package com.example.hilt.hilt

import com.example.hilt.network.CallInterceptorImpl
import com.example.hilt.network.NetworkService
import com.example.hilt.network.ResponseInterceptorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    // instantiate a specific class of the interface
    @ActivityScoped
    @CallInterceptor
    @Provides
    fun provideCallNetworkService(): NetworkService =
        NetworkService.Builder()
            .host("google.com")
            .protocol("HTTPS")
            .interceptor(CallInterceptorImpl())
            .build()

    @ActivityScoped
    @ResponseInterceptor
    @Provides
    fun provideResponseNetworkService(): NetworkService =
        NetworkService.Builder()
            .host("google.com")
            .protocol("HTTPS")
            .interceptor(ResponseInterceptorImpl())
            .build()
}