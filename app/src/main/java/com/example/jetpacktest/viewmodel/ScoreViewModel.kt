package com.example.jetpacktest.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

class ScoreViewModel : ViewModel() {

    private val TAG = this.javaClass.simpleName
    var scoreData: MutableLiveData<Int> = MutableLiveData()

    init {
        resetScore()
        Log.i(TAG, "Init ScoreViewModel : " + scoreData.value.toString())
    }

    fun getScore(): MutableLiveData<Int> {
        Log.i(TAG, "Get Score : " + scoreData.value.toString())
        return scoreData
    }

    fun addScore() {
        scoreData.value.let {
            scoreData.value = it?.plus(1)
            Log.i(TAG, "Add Score: " + scoreData.value.toString())
        }
    }

    fun resetScore() {
        scoreData.value = 0
        Log.i(TAG, "Reset Score: " + scoreData.value.toString())
    }
}
