package com.example.hilt.viewModel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(): ViewModel() {
    private val _statsLiveData = MutableLiveData<Int>()
    val statsLiveData = _statsLiveData

    private var i = 0
    private var runnable: Runnable? = null

    fun startStatsCollection(){
        val h = Handler(Looper.getMainLooper())
        runnable = Runnable {
            statsLiveData.value = ++i
            h.postDelayed(runnable!!, 500)
        }
        h.postDelayed(runnable!!, 500)
    }
}