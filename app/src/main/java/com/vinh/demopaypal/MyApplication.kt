package com.vinh.demopaypal

import android.app.Activity
import android.app.Application
import com.vinh.demopaypal.di.component.AppComponent
import com.vinh.demopaypal.di.component.DaggerAppComponent
import com.vinh.demopaypal.di.module.AppModule
import com.vinh.demopaypal.di.module.DataModule

class MyApplication : Application() {

    private var appComponent: AppComponent? = null

    companion object {
        private lateinit var instance: MyApplication

        fun getInstance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    // component
    private fun setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule(this))
            .build()
        appComponent?.inject(this)
    }

    fun getAppComponent(): AppComponent? {
        if (appComponent == null) {
            setupAppComponent()
        }
        return appComponent
    }

    fun get(activity: Activity): MyApplication {
        return activity.application as MyApplication
    }

}