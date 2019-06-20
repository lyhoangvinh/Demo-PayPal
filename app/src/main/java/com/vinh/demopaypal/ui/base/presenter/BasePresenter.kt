package com.vinh.demopaypal.ui.base.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.os.Bundle
import com.vinh.demopaypal.di.qualifier.ActivityContext
import com.vinh.demopaypal.ui.base.interfaces.BaseView
import com.vinh.demopaypal.ui.base.interfaces.Lifecycle
import com.vinh.demopaypal.ui.base.interfaces.Refreshable
import com.vinh.demopaypal.utils.CompleteCompletableObserver
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter<V : BaseView> internal constructor(
    @ActivityContext var context: Context
) : Lifecycle, Refreshable {
    private var view: V? = null

    private var mCompositeDisposable: CompositeDisposable? = null

    /**
     * @return [LifecycleOwner] associate with this presenter (host activities, fragments)
     */
    protected fun getLifeCircleOwner(): LifecycleOwner {
        return view as LifecycleOwner
    }

    fun getView(): V? = view

    fun bindView(view: V) {
        this.view = view
    }

    override fun onDestroy() {
        mCompositeDisposable?.dispose()
        this.view = null
    }

    override fun onCreate() {

    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onSaveInstanceState(outState: Bundle) {
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    }

    override fun refresh() {

    }

    /**
     * execute room
     */
    fun execute(addAction: () -> Unit, onComplete: (() -> Unit)?) {
        Completable.fromAction {
            addAction.invoke()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompleteCompletableObserver {

                override fun onComplete() {
                    onComplete?.invoke()
                }

                override fun onSubscribe(d: Disposable) {
                    if (mCompositeDisposable == null) {
                        mCompositeDisposable = CompositeDisposable()
                    }
                    mCompositeDisposable?.add(d)
                }
            })
    }

    fun execute(addAction: () -> Unit) {
        execute(addAction, null)
    }
}