package com.vinh.demopaypal.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vinh.demopaypal.data.dao.BeerDao
import com.vinh.demopaypal.data.dao.CardDao
import com.vinh.demopaypal.data.entinies.Beer
import com.vinh.demopaypal.data.entinies.Card


@Database(
    entities = [Beer::class, Card::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseManager : RoomDatabase() {
    abstract fun bearDao(): BeerDao
    abstract fun cardDao(): CardDao
}
