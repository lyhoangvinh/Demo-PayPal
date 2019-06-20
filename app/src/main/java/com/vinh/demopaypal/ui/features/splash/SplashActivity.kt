package com.vinh.demopaypal.ui.features.splash

import android.os.Bundle
import android.os.Handler
import com.vinh.demopaypal.R
import com.vinh.demopaypal.ui.base.activity.BaseActivity
import com.vinh.demopaypal.utils.NavigatorHelper
import javax.inject.Inject

class SplashActivity : BaseActivity() {
    override fun getLayoutResource() = R.layout.activity_splash

    @Inject
    lateinit var navigatorHelper: NavigatorHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        Handler().postDelayed({ navigatorHelper.navigateMainActivity() }, 500L)
    }
}