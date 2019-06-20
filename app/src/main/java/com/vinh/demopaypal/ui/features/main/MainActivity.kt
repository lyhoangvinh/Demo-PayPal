package com.vinh.demopaypal.ui.features.main

import com.vinh.demopaypal.ui.base.activity.BaseSingleFragmentActivity
import com.vinh.demopaypal.ui.features.main.shoppingcart.ShoppingCartFragment

class MainActivity : BaseSingleFragmentActivity<ShoppingCartFragment>() {
    override fun createFragment() = ShoppingCartFragment()
}
