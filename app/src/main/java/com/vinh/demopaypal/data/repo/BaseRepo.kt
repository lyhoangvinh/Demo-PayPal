package com.vinh.demopaypal.data.repo

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


abstract class BaseRepo {

    /**
     * Excute room
    </T> */

    fun execute(action: () -> Unit) {
        Completable.fromAction {
            action.invoke()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    interface OnSaveResultListener<T> {
        fun onSave(data: T, isRefresh: Boolean)
    }


}


