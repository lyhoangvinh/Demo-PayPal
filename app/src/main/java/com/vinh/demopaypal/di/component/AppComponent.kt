package com.vinh.demopaypal.di.component

import android.content.Context

import com.vinh.demopaypal.di.module.AppModule
import com.vinh.demopaypal.di.module.DataModule
import com.vinh.demopaypal.MyApplication
import com.vinh.demopaypal.data.dao.BeerDao
import com.vinh.demopaypal.data.dao.CardDao
import com.vinh.demopaypal.di.qualifier.ApplicationContext
import dagger.Component
import lyhoangvinh.com.myutil.thread.BackgroundThreadExecutor
import lyhoangvinh.com.myutil.thread.UIThreadExecutor
import javax.inject.Singleton

/**
 * Created by lyhoangvinh on 05/01/18.
 */
@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun getBackgroundThreadExecutor(): BackgroundThreadExecutor

    fun getUIThreadExecutor(): UIThreadExecutor

    fun getBearDao(): BeerDao

    fun getCardDao(): CardDao

    fun inject(app: MyApplication)
}