package com.example.hilt.hilt

import com.example.hilt.network.AppNetworkModuleForInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
// we can provide in which class we want inject, we add network module to component
@InstallIn(ActivityComponent::class)
abstract class NetworkModuleForInterfaces {
    //inject NetworkAdapter interface and AppNetworkAdapter instantiation
    // receive a implementation class for binding and return an interface
    @Binds
    abstract fun bindNetworkAdapterImpl(appNetworkAdapterForForInterfaceImpl: AppNetworkModuleForInterface): AppNetworkModuleForInterface

    //not working
//    @Binds
//    abstract fun bindNetworkAdapterAnotherImpl(appNetworkAdapterAnotherImpl: AppNetworkAdapterAnother): NetworkAdapter
}