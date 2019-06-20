package com.vinh.demopaypal.ui.features.main.shoppingcart

import android.arch.lifecycle.Observer
import android.content.Context
import com.vinh.demopaypal.data.repo.ShoppingCartRepo
import com.vinh.demopaypal.di.qualifier.ActivityContext
import com.vinh.demopaypal.di.scopes.PerFragment
import com.vinh.demopaypal.ui.base.presenter.BasePresenter
import javax.inject.Inject

@PerFragment
class ShoppingCartPresenter @Inject constructor(@ActivityContext ctx: Context, private val shoppingCartRepo: ShoppingCartRepo) :
    BasePresenter<ShoppingCartView>(ctx) {

    fun observe() {
        shoppingCartRepo.hardData()
        shoppingCartRepo.liveData().observe(getLifeCircleOwner(), Observer {
            getView()?.swapDataSuccess(it!!)
        })
    }
}