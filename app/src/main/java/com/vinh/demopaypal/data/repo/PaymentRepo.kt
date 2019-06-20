package com.vinh.demopaypal.data.repo

import com.vinh.demopaypal.data.dao.CardDao
import com.vinh.demopaypal.data.entinies.Card
import javax.inject.Inject

class PaymentRepo @Inject constructor(private val cardDao: CardDao) : BaseRepo() {

    fun liveData() = cardDao.liveData()

    fun insert(card: Card) {
        execute {
            cardDao.insert(card)
        }
    }

    fun delete(card: Card) {
        execute {
            cardDao.delete(card)
        }
    }
}