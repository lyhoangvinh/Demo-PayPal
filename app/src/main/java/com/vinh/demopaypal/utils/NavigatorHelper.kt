package com.vinh.demopaypal.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import com.vinh.demopaypal.Constants
import com.vinh.demopaypal.R
import com.vinh.demopaypal.data.entinies.Card
import com.vinh.demopaypal.ui.features.main.MainActivity
import com.vinh.demopaypal.ui.features.main.addpayment.AddPaymentFragment
import com.vinh.demopaypal.ui.features.main.confirmpayment.ConfirmPaymentFragment
import com.vinh.demopaypal.ui.features.main.paymentmethod.PaymentMethodFragment
import io.card.payment.CardIOActivity
import lyhoangvinh.com.myutil.navigation.ActivityNavigator
import lyhoangvinh.com.myutil.navigation.FragmentNavigator
import lyhoangvinh.com.myutil.navigation.Navigator

class NavigatorHelper(private var mNavigator: Navigator) {
    val SCAN_CARD_REQUEST = 1111

    fun NavigatorHelper(mNavigator: FragmentNavigator) {
        this.mNavigator = mNavigator
    }

    fun NavigatorHelper(mNavigator: ActivityNavigator) {
        this.mNavigator = mNavigator
    }

    fun NavigatorHelper(mNavigator: Navigator) {
        this.mNavigator = mNavigator
    }

    fun navigateMainActivity() {
        mNavigator.startActivity(MainActivity::class.java)
        mNavigator.finishActivity()
    }

    fun navigatePaymentMethodFragment() {
        mNavigator.replaceFragmentAndAddToBackStack(R.id.container, PaymentMethodFragment(), null, null)
    }

    fun navigateAddPaymentFragment() {
        mNavigator.replaceFragmentAndAddToBackStack(R.id.container, AddPaymentFragment(), null, null)
    }

    fun navigateCardIOActivity(ctx: Activity) {
        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        ctx.startActivityForResult(
            Intent(ctx, CardIOActivity::class.java).putExtra(
                CardIOActivity.EXTRA_SCAN_EXPIRY,
                true
            ), SCAN_CARD_REQUEST
        )
    }

    fun navigateConfirmPaymentFragment(card: Card) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.EXTRA_DATA, card)
        mNavigator.replaceFragmentAndAddToBackStack(R.id.container, ConfirmPaymentFragment(), bundle, null)
    }
}