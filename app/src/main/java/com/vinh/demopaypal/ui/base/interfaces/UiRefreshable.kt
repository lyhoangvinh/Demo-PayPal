package com.vinh.demopaypal.ui.base.interfaces

import com.vinh.demopaypal.ui.base.interfaces.Refreshable

/**
 * Indicate refreshable ui objects, eg. activity, fragment...
 */

interface UiRefreshable : Refreshable {
    fun doneRefresh()
    fun refreshWithUi()
    fun refreshWithUi(delay: Long)
    fun setRefreshEnabled(enabled: Boolean)
}
