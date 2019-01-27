package com.example.jetpacktest.noviewmodel

import android.util.Log

class ScoreDataGenerator {

    private val TAG = this.javaClass.simpleName
    private var scoreData: Int? = null

    init {
        resetScore()
        Log.i(TAG, "Init ScoreDataGenerator : " + scoreData.toString())
    }

    fun getScore(): Int? {
        if (scoreData == null) {
            resetScore()
        }
        Log.i(TAG, "Get Score : " + scoreData.toString())
        return scoreData
    }

    fun resetScore(): Int? {
        scoreData = 0
        Log.i(TAG, "Reset Score : " + scoreData.toString())
        return scoreData
    }

    fun addScore(): Int? {
        scoreData.let { scoreData = it?.plus(1) }
        Log.i(TAG, "Add Score: " + scoreData.toString())
        return scoreData
    }
}
