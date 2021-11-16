package com.example.hilt.hilt

import javax.inject.Qualifier

/*
    Add annotation classes for each interface implementation class
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CallInterceptor {}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ResponseInterceptor {
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkModuleForInstancesInterceptor {
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkModuleForInterfacesInterceptor {
}
