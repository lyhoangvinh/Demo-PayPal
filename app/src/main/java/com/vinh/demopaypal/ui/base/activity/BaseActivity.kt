package com.vinh.demopaypal.ui.base.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.vinh.demopaypal.R
import com.vinh.demopaypal.di.component.ActivityComponent
import com.vinh.demopaypal.di.component.DaggerActivityComponent
import com.vinh.demopaypal.di.module.ActivityModule
import com.vinh.demopaypal.ui.base.interfaces.BaseView
import com.vinh.demopaypal.ui.base.interfaces.UiRefreshable
import com.vinh.demopaypal.utils.getAppComponent
import com.vinh.demopaypal.utils.showToastMessage

/**
 * Created by lyhoangvinh on 04/01/18.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var dialog: Dialog? = null

    private var mActivityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        if (shouldPostponeTransition()) {
            ActivityCompat.postponeEnterTransition(this)
        }
    }

    // activity component, activity may or may not need this
    fun activityComponent(): ActivityComponent? {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(getAppComponent(this))
                .build()
        }
        return mActivityComponent
    }

    fun addFragment(@IdRes res: Int, fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction()
            .add(res, fragment, tag)
            .commit()
    }

    protected fun finishWithAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        } else {
            finish()
        }
    }

    /**
     * @return true if this activity should postpone transition (in case of destination view is in viewpager)
     */
    protected open fun shouldPostponeTransition(): Boolean {
        return false
    }

    protected open fun shouldRegisterEventBus(): Boolean {
        return false
    }

    abstract fun getLayoutResource(): Int

    override fun hideProgress() {
        if (this is UiRefreshable) {
            (this as UiRefreshable).doneRefresh()
        }
//        dialog?.let { if (it.isShowing) it.dismiss() }
        if (dialog != null && dialog!!.isShowing)
            dialog?.dismiss()
    }

    override fun showProgress() {
        hideProgress()
        if (dialog == null) {
            dialog = showLoadingDialog()
        }
        dialog?.show()
    }

    fun showLoadingDialog(): Dialog {
        val progressDialog = Dialog(this)
        progressDialog.let {
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setContentView(R.layout.progress_dialog)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

    override fun setProgress(show: Boolean) {
        if (show) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    override fun showMessage(message: String) {
        showToastMessage(message)
    }
}