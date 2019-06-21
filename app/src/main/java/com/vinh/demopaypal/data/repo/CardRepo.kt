package com.vinh.demopaypal.data.repo

import com.vinh.demopaypal.data.dao.CardDao
import com.vinh.demopaypal.data.entinies.Card
import javax.inject.Inject

class CardRepo @Inject constructor(private val cardDao: CardDao) : BaseRepo() {

    fun liveData() = cardDao.liveData()

    fun insert(cart: Card) {
        execute {
            cardDao.insert(cart)
        }
    }

    fun delete(cart: Card) {
        execute { cardDao.delete(cart) }
    }
}