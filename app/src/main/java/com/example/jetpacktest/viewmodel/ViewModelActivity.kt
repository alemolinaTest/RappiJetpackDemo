package com.example.jetpacktest.viewmodel

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView

import com.example.jetpacktest.R

class ViewModelActivity : AppCompatActivity() {

    lateinit var addScoreButton: Button
    lateinit var resetScoreButton: Button
    lateinit var scoreTextView: TextView

    //    Integer score = 0;
    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_model_main)

        Log.i(TAG, "onCreate launched" )

        scoreTextView = findViewById(R.id.scoreTextView)
        addScoreButton = findViewById(R.id.addScoreButton)
        resetScoreButton = findViewById(R.id.resetScoreButton)

        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        scoreViewModel.scoreData.observe(this, Observer { integer ->
           // Log.i(TAG, "Observed ScoreData changed: " + integer.toString())
            scoreTextView.text = integer.toString()
        })

        //addScore button listener
        addScoreButton.setOnClickListener { addScore() }
        //resetScore button listener
        resetScoreButton.setOnClickListener { resetScore() }
    }

    fun addScore() {
        scoreViewModel.addScore()
    }

    fun resetScore() {
        scoreViewModel.resetScore()
    }

    companion object {

        private val TAG = "ViewModelActivity"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ViewModelActivity::class.java)
            return intent
        }

    }


}
