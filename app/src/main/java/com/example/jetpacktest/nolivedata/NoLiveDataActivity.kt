package com.example.jetpacktest.nolivedata

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.jetpacktest.R
import com.example.jetpacktest.databinding.ViewModelObservableFieldBinding
import com.example.jetpacktest.others.Actions
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_model_observable_field.*

class NoLiveDataActivity : AppCompatActivity() {

    lateinit var viewModel: NoLiveDataViewModel

    private val compositeSubscriptions: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(observable_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Log.i(TAG, "onCreate launched" )
        val binding: ViewModelObservableFieldBinding = DataBindingUtil.setContentView(this, R.layout.view_model_observable_field)
        viewModel = ViewModelProviders.of(this).get(NoLiveDataViewModel::class.java)
        binding.viewModel = viewModel

        bindSubscription()

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

        private val TAG = "NoLiveDataActivity"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NoLiveDataActivity::class.java)
            return intent
        }

    }
}
