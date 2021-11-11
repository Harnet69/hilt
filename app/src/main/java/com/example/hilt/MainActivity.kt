package com.example.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hilt.database.DatabaseAdapter
import com.example.hilt.database.DatabaseService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {

    // field injection
    @Inject lateinit var databaseAdapter: DatabaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseAdapter.log("Welcoming log")

    }

    //method injection
    @Inject
    fun directToDb(databaseService: DatabaseService){
        databaseService.getFromDb()
    }
}