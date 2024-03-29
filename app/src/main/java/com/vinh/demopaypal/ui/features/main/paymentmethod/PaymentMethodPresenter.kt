package com.vinh.demopaypal.ui.features.main.paymentmethod

import android.arch.lifecycle.Observer
import android.content.Context
import com.vinh.demopaypal.data.entinies.Card
import com.vinh.demopaypal.data.repo.CardRepo
import com.vinh.demopaypal.di.qualifier.ActivityContext
import com.vinh.demopaypal.di.scopes.PerFragment
import com.vinh.demopaypal.ui.base.presenter.BasePresenter
import javax.inject.Inject

@PerFragment
class PaymentMethodPresenter @Inject constructor(@ActivityContext ctx: Context, private val paymentRepo: CardRepo) :
    BasePresenter<PaymentMethodView>(ctx) {

    fun observe() {
        paymentRepo.liveData().observe(getLifeCircleOwner(), Observer {
            getView()?.updatePayment(it!!)
            getView()?.showViewNoCard(it!!.isNullOrEmpty())
        })
    }

    fun delete(card: Card) {
        paymentRepo.delete(card)
    }
}