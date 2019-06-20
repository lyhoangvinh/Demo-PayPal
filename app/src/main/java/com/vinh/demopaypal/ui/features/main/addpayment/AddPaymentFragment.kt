package com.vinh.demopaypal.ui.features.main.addpayment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.stripe.android.model.Card
import com.vinh.demopaypal.R
import com.vinh.demopaypal.ui.base.activity.BaseSingleFragmentActivity
import com.vinh.demopaypal.ui.base.fragment.BasePresenterFragment
import com.vinh.demopaypal.utils.NavigatorHelper
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard
import kotlinx.android.synthetic.main.fragment_add_payment_method.*
import javax.inject.Inject

class AddPaymentFragment : BasePresenterFragment<AddPaymentView, AddPaymentPresenter>(), AddPaymentView {
    private val TAG = "AddPaymentMethod"

    @Inject
    lateinit var navigatorHelper: NavigatorHelper

    override fun getLayoutResource() = R.layout.fragment_add_payment_method

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent()?.inject(this)
    }

    @SuppressLint("CheckResult")
    override fun initialize(view: View, ctx: Context?) {
        super.initialize(view, ctx)
        (ctx as BaseSingleFragmentActivity<*>).setTitleToolBar(getString(R.string.add_card_title))
        (ctx).setOnClickAddListener(getString(R.string.action_save)) {
            if (isValidFormMain(
                    editCardNumber.text.toString(),
                    editCardNumber.text.toString(),
                    editExpiryDate.text.toString(),
                    editCVC.text.toString()
                )
            ) {
                if (!editCardNumber.isCardNumberValid) {
                    editCardNumber.error = "Invalid card number"
                    return@setOnClickAddListener
                }
                if (!editExpiryDate.isDateValid) {
                    editExpiryDate.error = "Invalid expiry date"
                    return@setOnClickAddListener
                }
                val number = editCardNumber.cardNumber
                val expiryDate = editExpiryDate.validDateFields
                val name = editCardName.text.toString()
                val cvc = editCVC.text.toString()
                val card = Card(number, expiryDate!![0], expiryDate[1], cvc)
                presenter.insertCard(name, card)
            }
        }

        llScanYourCard.setOnClickListener {
            Dexter.withActivity(ctx).withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        Log.d(TAG, "onPermissionGranted() called with: response = [$response]")
                        navigatorHelper.navigateCardIOActivity(ctx)
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {
                        Log.d(
                            TAG,
                            "onPermissionRationaleShouldBeShown() called with: permission = [$permission], token = [$token]"
                        )
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        Log.d(TAG, "onPermissionDenied() called with: response = [$response]")
                    }
                }).check()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(
            TAG,
            "onActivityResult() called with: requestCode = [$requestCode], resultCode = [$resultCode], data = [$data]"
        )
        if (requestCode == navigatorHelper.SCAN_CARD_REQUEST) {
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                val scanResult: CreditCard = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT)
                editCardNumber.setText(scanResult.cardNumber)
            } else {
                Log.e(TAG, "Scan was canceled")
            }
        }
    }

    override fun onBackPressed(): Boolean {
        fragmentManager?.popBackStack()
        return true
    }

    private fun isValidFormMain(cardName: String, cardNumber: String, expiryDate: String, cvc: String): Boolean {
        val validCardName = !TextUtils.isEmpty(cardName)
        if (!validCardName) {
            editCardName.error = "Please enter valid card name"
        }

        val validCardNumber = !TextUtils.isEmpty(cardNumber)
        if (!validCardNumber) {
            editCardNumber.error = "Please enter valid card number"
        }

        val validExpiryDate = !TextUtils.isEmpty(expiryDate)
        if (!validExpiryDate) {
            editExpiryDate.error = "Please enter valid expiry date"
        }

        val validCVC = !TextUtils.isEmpty(cvc)
        if (!validCVC) {
            editCVC.error = "Please enter valid CVC"
        }

        return validCardName && validCardNumber && validExpiryDate && validCVC
    }

    override fun addPaymentSuccess() {
        onBackPressed()
    }
}