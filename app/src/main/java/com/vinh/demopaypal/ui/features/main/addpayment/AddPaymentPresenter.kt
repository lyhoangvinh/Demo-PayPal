package com.vinh.demopaypal.ui.features.main.addpayment

import android.content.Context
import com.stripe.android.Stripe
import com.stripe.android.TokenCallback
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import com.vinh.demopaypal.Constants
import com.vinh.demopaypal.data.repo.CardRepo
import com.vinh.demopaypal.di.qualifier.ActivityContext
import com.vinh.demopaypal.di.scopes.PerFragment
import com.vinh.demopaypal.ui.base.presenter.BasePresenter
import javax.inject.Inject

@PerFragment
class AddPaymentPresenter @Inject constructor(@ActivityContext ctx: Context, private val cardRepo: CardRepo) :
    BasePresenter<AddPaymentView>(ctx) {

    fun insertCard(name: String, card: Card) {
        getView()?.showProgress()
        val stripe = Stripe(context, Constants.KEY)
        stripe.createToken(card, object : TokenCallback {
            override fun onError(error: Exception) {
                getView()?.hideProgress()
            }

            override fun onSuccess(token: Token) {
                //Todo:  addCardToServer(token)
                getView()?.hideProgress()
                cardRepo.insert(
                    com.vinh.demopaypal.data.entinies.Card(
                        token = token.id,
                        brand = token.card.brand,
                        country = token.card.country,
                        cvcCheck = token.card.cvcCheck,
                        expMonth = token.card.expMonth,
                        expYear = token.card.expYear,
                        name = name,
                        last4 = token.card.last4
                    )
                )
                getView()?.addPaymentSuccess()
            }
        })
    }
}