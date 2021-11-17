package com.example.hilt.database

import android.util.Log
import com.example.hilt.TAG
import javax.inject.Inject

open class DatabaseService @Inject constructor() {
    
    fun getFromDb(){
        Log.d(TAG, "Database service :  Database service was injected")
    }
}