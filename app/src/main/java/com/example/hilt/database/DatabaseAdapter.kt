package com.example.hilt.database

import android.content.Context
import android.util.Log
import com.example.hilt.TAG
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

//construction injection
class DatabaseAdapter @Inject constructor(@ActivityContext private val context: Context,
                                          private var databaseService: DatabaseService) {
    fun log(msg: String){
        Log.d(TAG, "Database adapter: $msg")
        Log.d(TAG, "Context is available: $context")
        databaseService.getFromDb()
    }

    fun justForTestPurposes(num1: Int, num2: Int) = num1*num2
}