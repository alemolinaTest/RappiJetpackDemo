package com.example.jetpacktest.livedata

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.jetpacktest.R
import com.example.jetpacktest.databinding.ViewModelLiveDataBinding
import com.example.jetpacktest.others.Actions
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_model_live_data.*

class LiveDataActivity : AppCompatActivity() {

    lateinit var viewModel: LiveDataViewModel

    private val compositeSubscriptions: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(live_data_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.i(TAG, "onCreate launched" )
        val binding: ViewModelLiveDataBinding = DataBindingUtil.setContentView(this, R.layout.view_model_live_data)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
        binding.viewModel = viewModel

        bindSubscription()

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

        private val TAG = "NoLiveDataActivity"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LiveDataActivity::class.java)
            return intent
        }

    }
}
