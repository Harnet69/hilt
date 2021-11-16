package com.example.hilt.network

import android.util.Log
import com.example.hilt.TAG
import javax.inject.Inject

class AppNetworkModuleForInterface @Inject constructor(): AppNetworkModuleInterface {
    override fun log(msg: String) {
        Log.d(TAG, "AppNetworkAdapter $msg")
    }
}