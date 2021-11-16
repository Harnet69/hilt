package com.example.hilt.network

import android.util.Log
import com.example.hilt.TAG
import javax.inject.Inject

class AppNetworkAdapterForInstances @Inject constructor() {
    fun log(msg: String) {
        Log.d(TAG, "Another AppNetworkAdapter $msg")
    }
}