package com.vinh.demopaypal.di.component

import android.arch.lifecycle.LifecycleOwner
import android.support.v4.app.FragmentManager
import com.vinh.demopaypal.di.module.ActivityModule

import com.vinh.demopaypal.di.qualifier.ActivityFragmentManager
import com.vinh.demopaypal.di.scopes.PerActivity
import com.vinh.demopaypal.ui.features.splash.SplashActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    @ActivityFragmentManager
    fun defaultFragmentManager(): FragmentManager

    fun lifeCycleOwner(): LifecycleOwner

    fun inject(activity: SplashActivity)
}