package com.vinh.demopaypal.ui.features.main.confirmpayment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.vinh.demopaypal.Constants
import com.vinh.demopaypal.R
import com.vinh.demopaypal.data.entinies.Card
import com.vinh.demopaypal.ui.base.fragment.BasePresenterFragment
import com.vinh.demopaypal.utils.NavigatorHelper
import com.vinh.demopaypal.utils.setCardTypeImageBitmap
import com.vinh.demopaypal.utils.setVisible
import kotlinx.android.synthetic.main.fragment_credit_card_detail.*
import javax.inject.Inject

class ConfirmPaymentFragment : BasePresenterFragment<ConfirmPaymentView, ConfirmPaymentPresenter>(),
    ConfirmPaymentView {

    @Inject
    lateinit var navigatorHelper: NavigatorHelper

    override fun getLayoutResource() = R.layout.fragment_credit_card_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent()?.inject(this)
    }

    override fun initialize(view: View, ctx: Context?) {
        super.initialize(view, ctx)
        if (arguments != null) {
            val card: Card = arguments!!.getParcelable(Constants.EXTRA_DATA)!!
            imgCardType.setCardTypeImageBitmap(card.brand.toString())
            tvName.text = card.name
            tvCardNumber.text = String.format("•••• •••• •••• ${card.last4}")
            llChooseThisCard.setVisible(true)
            btnChooseThisCard.setOnClickListener { presenter.confirm() }
        }
    }

    override fun confirmSuccess() {
        navigatorHelper.navigateMainActivity()
    }

    override fun onBackPressed(): Boolean {
        fragmentManager?.popBackStack()
        return true
    }
}