package com.example.hilt.network

import android.util.Log
import com.example.hilt.TAG
import javax.inject.Inject

class ResponseInterceptorImpl @Inject constructor(): Interceptor {
    override fun log(msg: String) {
        Log.d(TAG, "Response interceptor: $msg")
    }
}