package com.vinh.demopaypal.ui.features.main.confirmpayment

import android.content.Context
import com.vinh.demopaypal.di.qualifier.ActivityContext
import com.vinh.demopaypal.di.scopes.PerFragment
import com.vinh.demopaypal.ui.base.presenter.BasePresenter
import javax.inject.Inject

@PerFragment
class ConfirmPaymentPresenter @Inject constructor(@ActivityContext ctx: Context) :
    BasePresenter<ConfirmPaymentView>(ctx) {

    fun confirm() {
        //Todo: call api confirm
        getView()?.showMessage("Pay Success")
        getView()?.confirmSuccess()
    }
}