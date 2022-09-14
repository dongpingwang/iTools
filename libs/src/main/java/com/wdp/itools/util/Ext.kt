package com.wdp.itools.util

import android.os.IInterface
import android.os.RemoteCallbackList
import android.widget.SeekBar

fun <T> T?.isNotNull(block: (T) -> Unit) {
    if (this != null) {
        block.invoke(this)
    }
}

fun <A, B> withNonNull(a: A?, b: B?, block: (A, B) -> Unit) {
    if (a != null && b != null) {
        block.invoke(a, b)
    }
}

fun String?.isNotEmpty(block: (String) -> Unit) {
    if (!this.isNullOrEmpty()) {
        block.invoke(this)
    }
}

fun <E : IInterface> RemoteCallbackList<E>.broadcast(block: (E) -> Unit) {
    beginBroadcast()
    for (i in 0 until registeredCallbackCount) {
        block.invoke(getBroadcastItem(i))
    }
    finishBroadcast()
}

fun <E> MutableList<E>.addIfNotExist(element: E): Boolean {
    return !contains(element) && add(element)
}
