package com.example.jetpacktest.nolivedata

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.example.jetpacktest.others.Actions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class NoLiveDataViewModel : ViewModel(), LifecycleObserver {

    private var compositeDisposable = CompositeDisposable()

    private val actionsSubject: PublishSubject<Actions> = PublishSubject.create()


    private val TAG = this.javaClass.simpleName
    val scoreData = ObservableField<Int>()
    val showData = ObservableBoolean()

    init {
        this.scoreData.set(0)
        Log.i(TAG, "Init NoLiveDataViewModel : " + scoreData.get().toString())
    }

    fun resetScore() {
        scoreData.set(0)
        Log.i(TAG, "Reset Score: " + scoreData.get().toString())
        compositeDisposable.clear()
    }

    fun getNumbers() {

        compositeDisposable.add(Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    scoreData.set(it.toInt())
                    Log.i(TAG, "Assign Score: " + scoreData.get().toString())
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

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        compositeDisposable.dispose()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        compositeDisposable = CompositeDisposable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        compositeDisposable.clear()
    }
}
