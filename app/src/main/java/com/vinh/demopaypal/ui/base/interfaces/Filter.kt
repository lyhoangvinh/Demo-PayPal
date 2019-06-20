package com.vinh.demopaypal.ui.base.interfaces

interface Filter<T, E> {
    fun isMatched(obj: T, text: E): Boolean
}
