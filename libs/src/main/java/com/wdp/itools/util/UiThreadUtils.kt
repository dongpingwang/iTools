package com.wdp.itools.util

import android.os.Handler
import android.os.Looper

/**
 * author: Dongping Wang
 * date: 2022/9/14 0:12
 * description: UiThreadUtils
 */
object UiThreadUtils {

    @Volatile
    private var mainHandler: Handler? = null

    fun with(mainHandler: Handler) {
        this.mainHandler = mainHandler
    }

    private fun handler(): Handler {
        if (mainHandler == null) {
            synchronized(UiThreadUtils::class) {
                if (mainHandler == null) {
                    mainHandler = Handler(Looper.getMainLooper())
                }
            }
        }
        return mainHandler!!
    }

    fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

    fun runOnUiThread(action: Runnable) {
        if (isMainThread()) {
            action.run()
        } else {
            handler().post(action)
        }
    }

}