package com.vinh.demopaypal.ui.features.main.paymentmethod

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.vinh.demopaypal.R
import com.vinh.demopaypal.data.entinies.Card
import com.vinh.demopaypal.ui.base.activity.BaseSingleFragmentActivity
import com.vinh.demopaypal.ui.base.fragment.BasePresenterFragment
import com.vinh.demopaypal.ui.base.interfaces.PlainConsumer
import com.vinh.demopaypal.ui.widget.SwipeToDeleteCallback
import com.vinh.demopaypal.utils.NavigatorHelper
import com.vinh.demopaypal.utils.setVisible
import kotlinx.android.synthetic.main.fragment_payment_method.*
import javax.inject.Inject

class PaymentMethodFragment : BasePresenterFragment<PaymentMethodView, PaymentMethodPresenter>(), PaymentMethodView {

    @Inject
    lateinit var navigatorHelper: NavigatorHelper

    private var adapter: PaymentMethodAdapter? = null

    override fun getLayoutResource() = R.layout.fragment_payment_method

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent()?.inject(this)
    }

    override fun initialize(view: View, ctx: Context?) {
        super.initialize(view, ctx)
        (ctx as BaseSingleFragmentActivity<*>).setTitleToolBar(getString(R.string.your_payment_method))
        (ctx).setOnClickAddListener(getString(R.string.action_add)) {
            navigatorHelper.navigateAddPaymentFragment()
        }
        presenter.observe()
        adapter = PaymentMethodAdapter(ArrayList()).setOnClickItemCardListener {
            navigatorHelper.navigateConfirmPaymentFragment(it)
        }
        rcvPayment.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rcvPayment.adapter = adapter
        ItemTouchHelper(SwipeToDeleteCallback(ctx, object : PlainConsumer<Int> {
            override fun accept(t: Int) {
                presenter.delete(adapter?.getItem(t) as Card)
            }
        })).attachToRecyclerView(rcvPayment)
        noCardView.setOnClickListener { navigatorHelper.navigateAddPaymentFragment() }
    }

    override fun showViewNoCard(show: Boolean) {
        noCardView.setVisible(show)
    }

    override fun onBackPressed(): Boolean {
        fragmentManager?.popBackStack()
        return true
    }

    override fun updatePayment(cards: List<Card>) {
        adapter?.updatePaymentMethod(cards)
    }
}