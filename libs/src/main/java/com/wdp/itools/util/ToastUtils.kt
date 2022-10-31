package com.wdp.itools.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * author: Dongping Wang
 * date: 2022/9/24 14:10
 * description: ToastUtils.kt
 */
object ToastUtils {

    private var toast: Toast? = null

    fun show(msg: String) {
        show(AppUtils.getContext(), msg, Toast.LENGTH_SHORT)
    }

    fun show(@StringRes msgResId: Int) {
        show(AppUtils.getContext(), ResUtils.getString(msgResId), Toast.LENGTH_SHORT)
    }

    fun showLong(msg: String) {
        show(AppUtils.getContext(), msg, Toast.LENGTH_LONG)
    }

    fun showLong(@StringRes msgResId: Int) {
        show(AppUtils.getContext(), ResUtils.getString(msgResId), Toast.LENGTH_LONG)
    }

    private fun show(context: Context, msg: String, duration: Int) {
        toast?.cancel()
        toast = Toast.makeText(context, msg, duration).apply {
            UiThreadUtils.runOnUiThread {
                show()
            }
        }
    }
}



