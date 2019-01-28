package com.example.jetpacktest.noviewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.jetpacktest.R
import com.example.jetpacktest.others.Actions

class NoViewModelActivity : AppCompatActivity() {

    lateinit var addScoreButtonNV: Button
    lateinit var resetScoreButtonNV: Button
    lateinit var scoreTextViewNV: TextView

    private lateinit var src: ScoreDataGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_view_model_main)

        Log.i(TAG, "onCreate launched" )

        scoreTextViewNV = findViewById(R.id.scoreTextViewNV)
        addScoreButtonNV = findViewById(R.id.addScoreButtonNV)
        resetScoreButtonNV = findViewById(R.id.resetScoreButtonNV)

        src = ScoreDataGenerator()
        //addScore button listener
        addScoreButtonNV.setOnClickListener { addScore() }
        //resetScore button listener
        resetScoreButtonNV.setOnClickListener { resetScore() }

        src.getScore()?.let { scoreTextViewNV.text = it.toString() }

    }

    fun addScore() {
        src.addScore()?.let { scoreTextViewNV.text = it.toString() }
    }

    fun resetScore() {
        src.resetScore()?.let { scoreTextViewNV.text = it.toString() }
    }

    private fun eventListener(actions: Actions) {
        when (actions) {
            Actions.OnClickBack -> onBackButtonPressed()
        }
    }

    fun onBackButtonPressed(){
        finishAffinity()
    }


    companion object {

        private val TAG = "NoViewModelActivity"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NoViewModelActivity::class.java)
            return intent
        }
    }

}
