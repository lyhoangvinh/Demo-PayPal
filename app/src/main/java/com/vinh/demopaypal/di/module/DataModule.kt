package com.vinh.demopaypal.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.vinh.demopaypal.data.DatabaseManager
import com.vinh.demopaypal.data.dao.BeerDao
import com.vinh.demopaypal.data.dao.CardDao
import com.vinh.demopaypal.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(@ApplicationContext private var context: Application) {

    @Singleton
    @Provides
    fun providesRoomDatabase(): DatabaseManager {
        return Room.databaseBuilder(context, DatabaseManager::class.java, "my-database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideBearDao(databaseManager: DatabaseManager): BeerDao = databaseManager.bearDao()

    @Provides
    @Singleton
    fun provideCardDao(databaseManager: DatabaseManager): CardDao = databaseManager.cardDao()
}