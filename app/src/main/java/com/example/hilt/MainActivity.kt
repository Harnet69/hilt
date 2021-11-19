package com.example.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.hilt.database.DatabaseAdapter
import com.example.hilt.database.DatabaseService
import com.example.hilt.hilt.CallInterceptor
import com.example.hilt.network.AppNetworkModuleForInterface
import com.example.hilt.network.NetworkService
import com.example.hilt.viewModel.StatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var databaseAdapter: DatabaseAdapter

    //interface injecting(not instance of a class this interface implemented)
//    @Inject lateinit var interfaceForAppNetworkAdapterForInterface: AppNetworkModuleForInterface

    @CallInterceptor
    @Inject
    lateinit var networkService1: NetworkService

    @CallInterceptor
    @Inject
    lateinit var networkService2: NetworkService

    @CallInterceptor
    @Inject
    lateinit var networkService3: NetworkService

    //inject ViewModel class
    private val statsViewModel: StatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statsViewModel.statsLiveData.observe(this, {
            Log.d(TAG, "The new stat is: $it")
        })
//        statsViewModel.startStatsCollection()

        databaseAdapter.log("Welcoming log")

//        interfaceForAppNetworkAdapterForInterface.log("Run injected interface")

        // test a Singleton scope
        networkService1.preformNetworkCall()
        networkService2.preformNetworkCall()
        networkService3.preformNetworkCall()
    }

    //method injection
    @Inject
    fun directToDb(databaseService: DatabaseService) {
        databaseService.getFromDb()
    }
}