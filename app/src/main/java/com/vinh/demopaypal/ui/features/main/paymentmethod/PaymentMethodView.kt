package com.vinh.demopaypal.ui.features.main.paymentmethod

import com.vinh.demopaypal.data.entinies.Card
import com.vinh.demopaypal.ui.base.interfaces.BaseView

interface PaymentMethodView : BaseView {
    fun showViewNoCard(show: Boolean)
    fun updatePayment(cards: List<Card>)
}