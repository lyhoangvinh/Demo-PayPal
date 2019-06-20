package com.vinh.demopaypal.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.vinh.demopaypal.data.entinies.Card

@Dao
interface CardDao : BaseDao<Card> {
    @Query("SELECT * FROM Card")
    fun liveData(): LiveData<List<Card>>
}