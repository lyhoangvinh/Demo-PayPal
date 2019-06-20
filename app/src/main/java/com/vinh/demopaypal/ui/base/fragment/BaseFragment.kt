package com.vinh.demopaypal.ui.base.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinh.demopaypal.ui.base.interfaces.BaseView
import com.vinh.demopaypal.di.component.FragmentComponent
import com.vinh.demopaypal.utils.getAppComponent
import com.vinh.demopaypal.di.module.FragmentModule
import com.vinh.demopaypal.ui.base.interfaces.UiRefreshable
import com.vinh.demopaypal.R
import com.vinh.demopaypal.di.component.DaggerFragmentComponent
import com.vinh.demopaypal.utils.hideKeyboard
import com.vinh.demopaypal.utils.showToastMessage

/**
 * Created by lyhoangvinh on 04/01/18.
 */

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment : Fragment(), BaseView {

    private var mFragmentComponent: FragmentComponent? = null

    private var dialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view, activity)
    }

    fun fragmentComponent(): FragmentComponent? {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule(this))
                .appComponent(getAppComponent(this))
                .build()
        }
        return mFragmentComponent
    }

    protected abstract fun initialize(view: View, ctx: Context?)

    abstract fun getLayoutResource(): Int

    override fun hideProgress() {
        if (this is UiRefreshable) {
            (this as UiRefreshable).doneRefresh()
        }
        dialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        dialog = showLoadingDialog(activity!!)
    }

    private fun showLoadingDialog(ctx: Context): Dialog {
        Dialog(ctx).let {
            it.show()
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

    fun finishFragment() {
        hideKeyboard()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.finishAfterTransition()
        } else {
            activity?.finish()
        }
    }

    override fun showMessage(message: String) {
        showToastMessage(message)
    }

    /**
     * @return true if fragment should handle back press, false if not (activity will handle back press event)
     */
    open fun onBackPressed(): Boolean {
        return false
    }
}