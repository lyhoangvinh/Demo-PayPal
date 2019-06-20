package com.vinh.demopaypal.ui.features.main.shoppingcart

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vinh.demopaypal.R
import com.vinh.demopaypal.data.entinies.Beer
import com.vinh.demopaypal.ui.base.activity.BaseSingleFragmentActivity
import com.vinh.demopaypal.ui.base.fragment.BasePresenterFragment
import com.vinh.demopaypal.utils.NavigatorHelper
import kotlinx.android.synthetic.main.fragment_shoping_cart.*
import javax.inject.Inject

class ShoppingCartFragment : BasePresenterFragment<ShoppingCartView, ShoppingCartPresenter>(), ShoppingCartView {

    @Inject
    lateinit var navigatorHelper: NavigatorHelper

    private var adapter: ShoppingCartAdapter? = null

    private var count = 0

    private var price = 0.0

    override fun getLayoutResource() = R.layout.fragment_shoping_cart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent()?.inject(this)
    }

    override fun initialize(view: View, ctx: Context?) {
        super.initialize(view, ctx)
        (ctx as BaseSingleFragmentActivity<*>).setTitleToolBar(getString(R.string.shopping))
        adapter = ShoppingCartAdapter(ArrayList()).onClickItemListener {
            count++
            tvCount.text = String.format("$count Select")
            price += it.price!!
            tvTotalPrice.text = String.format("USD $price")
        }
        rcvShopping.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rcvShopping.adapter = adapter
        presenter.observe()

        btnPayNow.setOnClickListener { navigatorHelper.navigatePaymentMethodFragment() }
    }

    override fun swapDataSuccess(data: List<Beer>) {
        adapter?.updateShoppingCart(data)
    }
}