package com.example.hilt.network

import android.util.Log
import com.example.hilt.TAG

/*
    Builder pattern. Shows how we can inject more complex types.
    Allows to create a network service through a builder, which is accessible
    outside a class. Build whatever parameter we need and skip not needed parameters, call build()
    and get an instance of network service
 */
class NetworkService private constructor(builder: Builder) {
    val protocol: String?
    val host: String?
    val path: String?
    val interceptor: Interceptor?

    init {
        this.protocol = builder.protocol
        this.host = builder.host
        this.path = builder.path
        this.interceptor = builder.interceptor
    }

    fun preformNetworkCall(){
        interceptor?.log("Call preformed")
        Log.d(TAG, "preformNetworkCall: preformed $this")
    }

    class Builder {
        var protocol: String? = null
            // can't set it directly
            private set
        var host: String? = null
            // can't set it directly
            private set
        var path: String? = null
            // can't set it directly
            private set
        var interceptor: Interceptor? = null
            private set

        //allows to set variables and return Builder class instance
        fun protocol(protocol: String) = apply { this.protocol = protocol }
        fun host(host: String) = apply { this.host = host }
        fun path(path: String) = apply { this.path = path }
        fun interceptor(interceptor: Interceptor) = apply{this.interceptor = interceptor}

        fun build() = NetworkService(this)
    }
}
