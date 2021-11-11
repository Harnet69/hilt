package com.example.hilt.network

import android.util.Log
import com.example.hilt.TAG
import javax.inject.Inject

class CallInterceptorImpl @Inject constructor(): Interceptor {
    override fun log(msg: String) {
        Log.d(TAG, "Call interceptor: $msg")
    }
}