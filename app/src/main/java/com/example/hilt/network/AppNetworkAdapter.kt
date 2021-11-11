package com.example.hilt.network

import android.util.Log
import com.example.hilt.TAG
import javax.inject.Inject

class AppNetworkAdapter @Inject constructor(): NetworkAdapter {
    override fun log(msg: String) {
        Log.d(TAG, "AppNetworkAdapter $msg")
    }
}