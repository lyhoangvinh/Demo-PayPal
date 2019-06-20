package com.vinh.demopaypal.di.module

import  android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.vinh.demopaypal.ui.base.activity.BaseActivity
import com.vinh.demopaypal.utils.NavigatorHelper
import com.vinh.demopaypal.di.qualifier.ActivityContext
import com.vinh.demopaypal.di.qualifier.ActivityFragmentManager
import dagger.Module
import dagger.Provides
import lyhoangvinh.com.myutil.navigation.ActivityNavigator
import lyhoangvinh.com.myutil.navigation.Navigator

@Module
class ActivityModule(private var activity: BaseActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    fun provideActivity(): FragmentActivity = activity

    @Provides
    @ActivityFragmentManager
    fun provideFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    fun provideNavigator(): Navigator = ActivityNavigator(activity)

    @Provides
    fun provideNavigatorHelper(navigator: Navigator): NavigatorHelper = NavigatorHelper(navigator)

    @Provides
    fun provideLifeCycleOwner(): LifecycleOwner = activity
}