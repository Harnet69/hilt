package com.example.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hilt.database.DatabaseAdapter
import com.example.hilt.database.DatabaseService
import com.example.hilt.hilt.CallInterceptor
import com.example.hilt.network.AppNetworkModuleForInterface
import com.example.hilt.network.NetworkService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {

    // field injection
    @Inject lateinit var databaseAdapter: DatabaseAdapter
    //interface injecting(not instance of a class this interface implemented)
//    @Inject lateinit var interfaceForAppNetworkAdapterForInterface: AppNetworkModuleForInterface

    @CallInterceptor
    @Inject lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseAdapter.log("Welcoming log")

//        interfaceForAppNetworkAdapterForInterface.log("Run injected interface")

        networkService.preformNetworkCall()
    }

    //method injection
    @Inject
    fun directToDb(databaseService: DatabaseService){
        databaseService.getFromDb()
    }
}