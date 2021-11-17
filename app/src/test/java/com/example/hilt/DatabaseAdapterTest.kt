package com.example.hilt

import android.content.Context
import com.example.hilt.database.DatabaseAdapter
import com.example.hilt.database.DatabaseService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DatabaseAdapterTest {
    @Mock
    lateinit var mockContext: Context

    @Mock
    lateinit var mockDatabaseService: DatabaseService

    @Test
    fun testDatabaseAdapter(){
        // instantiate database adapter
        val adapter = DatabaseAdapter(mockContext, mockDatabaseService)
        val multRes = adapter.justForTestPurposes(2,2)
        assert(multRes == 4)
    }
}