package com.example.jetpacktest.livedata

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.jetpacktest.others.Actions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class LiveDataViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val actionsSubject: PublishSubject<Actions> = PublishSubject.create()

    private val TAG = this.javaClass.simpleName
    val scoreData = MutableLiveData<Int>()

    init {
        this.scoreData.value = 0
        Log.i(TAG, "Init NoLiveDataViewModel : " + scoreData.value.toString())
    }

    fun resetScore() {
        scoreData.value = 0
        Log.i(TAG, "Reset Score: " + scoreData.value.toString())
    }

    fun getNumbers() {

        compositeDisposable.add(Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    scoreData.value = it.toInt()
                    Log.i(TAG, "Assign Score: " + scoreData.value.toString())
                }
                .doOnComplete { resetScore() }
                .subscribe())
    }

    fun onClickBack() {
        actionsSubject.onNext(Actions.OnClickBack)
    }

    fun observableActionSubject(): Observable<Actions> {
        return actionsSubject.observeOn(AndroidSchedulers.mainThread())
    }
}
