package com.vinh.demopaypal.ui.base.interfaces

import android.os.Bundle
import com.vinh.demopaypal.ui.base.interfaces.Destroyable

interface Lifecycle : Destroyable {
    fun onCreate()
    fun onStart()
    fun onStop()
    fun onSaveInstanceState(outState: Bundle)
    fun onRestoreInstanceState(savedInstanceState: Bundle)
}