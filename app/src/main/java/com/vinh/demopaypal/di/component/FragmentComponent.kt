package com.vinh.demopaypal.di.component

import android.arch.lifecycle.LifecycleOwner
import com.vinh.demopaypal.di.module.FragmentModule
import com.vinh.demopaypal.di.scopes.PerFragment
import com.vinh.demopaypal.ui.features.main.addpayment.AddPaymentFragment
import com.vinh.demopaypal.ui.features.main.confirmpayment.ConfirmPaymentFragment
import com.vinh.demopaypal.ui.features.main.paymentmethod.PaymentMethodFragment
import com.vinh.demopaypal.ui.features.main.shoppingcart.ShoppingCartFragment
import dagger.Component

@PerFragment
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun lifeCycleOwner(): LifecycleOwner

    fun inject(fragment: ShoppingCartFragment)

    fun inject(fragment: PaymentMethodFragment)

    fun inject(fragment: AddPaymentFragment)

    fun inject(fragment: ConfirmPaymentFragment)
}