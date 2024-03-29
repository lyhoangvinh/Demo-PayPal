package com.vinh.demopaypal.ui.base.fragment

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.View
import com.vinh.demopaypal.ui.base.interfaces.BaseView
import com.vinh.demopaypal.ui.base.presenter.BasePresenter
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
abstract class BasePresenterFragment<V : BaseView, P : BasePresenter<V>> : BaseFragment() {

    @Inject
    protected lateinit var presenter: P

    @CallSuper
    override fun initialize(view: View, ctx: Context?) {
        presenter.bindView(getViewLayer())
        presenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null)
            presenter.onRestoreInstanceState(savedInstanceState)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun getViewLayer(): V {
        return this as V
    }
}
