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
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerFragment
class AddPaymentPresenter @Inject constructor(@ActivityContext ctx: Context, private val cardRepo: CardRepo) :
    BasePresenter<AddPaymentView>(ctx) {

    fun insertCard(name: String, card: Card) {
        getView()?.showProgress()
        val stripe = Stripe(context, Constants.KEY)

        val single = Single.create(SingleOnSubscribe<Token> {
            stripe.createToken(card, object : TokenCallback {
                override fun onSuccess(token: Token?) {
                    it.onSuccess(token!!)
                }

                override fun onError(error: java.lang.Exception?) {
                    it.onError(error!!)
                }
            })
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Todo:  addCardToServer(token)
                getView()?.hideProgress()
                cardRepo.insert(
                    com.vinh.demopaypal.data.entinies.Card(
                        token = it.id,
                        brand = it.card.brand,
                        country = it.card.country,
                        cvcCheck = it.card.cvcCheck,
                        expMonth = it.card.expMonth,
                        expYear = it.card.expYear,
                        name = name,
                        last4 = it.card.last4
                    )
                )
                getView()?.addPaymentSuccess()
            }, {
                error {
                    getView()?.hideProgress()

                }
            })

        disposeOnDestroy(single)
    }
}