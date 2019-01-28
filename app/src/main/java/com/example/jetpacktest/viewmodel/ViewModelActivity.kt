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
import com.example.jetpacktest.others.Actions
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.no_view_model_main.*

class ViewModelActivity : AppCompatActivity() {

    lateinit var addScoreButton: Button
    lateinit var resetScoreButton: Button
    lateinit var scoreTextView: TextView

    private val compositeSubscriptions: CompositeDisposable = CompositeDisposable()

    //    Integer score = 0;
    lateinit var viewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_model_main)

        Log.i(TAG, "onCreate launched" )

        setSupportActionBar(no_vm_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        scoreTextView = findViewById(R.id.scoreTextView)
        addScoreButton = findViewById(R.id.addScoreButton)
        resetScoreButton = findViewById(R.id.resetScoreButton)

        viewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        viewModel.scoreData.observe(this, Observer { integer ->
           // Log.i(TAG, "Observed ScoreData changed: " + integer.toString())
            scoreTextView.text = integer.toString()
        })

        //addScore button listener
        addScoreButton.setOnClickListener { addScore() }
        //resetScore button listener
        resetScoreButton.setOnClickListener { resetScore() }

        bindSubscription()
    }

    fun addScore() {
        viewModel.addScore()
    }

    fun resetScore() {
        viewModel.resetScore()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscriptions.clear()
    }

    private fun bindSubscription() {
        compositeSubscriptions.addAll(
                viewModel.observableActionSubject().subscribe(this::eventListener)
        )
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

        private val TAG = "ViewModelActivity"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ViewModelActivity::class.java)
            return intent
        }
    }
}
