package com.vinh.demopaypal.ui.features.main.shoppingcart

import com.vinh.demopaypal.data.entinies.Beer
import com.vinh.demopaypal.ui.base.interfaces.BaseView

interface ShoppingCartView : BaseView {
    fun swapDataSuccess(data: List<Beer>)
}