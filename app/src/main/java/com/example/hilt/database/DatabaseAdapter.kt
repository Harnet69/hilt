package com.example.hilt.database

import android.util.Log
import com.example.hilt.TAG
import javax.inject.Inject

//construction injection
class DatabaseAdapter @Inject constructor(var databaseService: DatabaseService) {
    fun log(msg: String){
        Log.d(TAG, "Database adapter: $msg")
        databaseService.getFromDb()
    }
}