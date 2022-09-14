package com.wdp.itools.util

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception

/**
 * author: Dongping Wang
 * date: 2022/9/15 1:02
 * description: ServiceUtils
 */
object ServiceUtils {

    fun startService(intent: Intent) {
        try {
            AppUtils.getContext().startService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun startService(cls: Class<*>) {
        startService(Intent(AppUtils.getContext(), cls))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startForegroundService(intent: Intent) {
        try {
            AppUtils.getContext().startForegroundService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isServiceRunning() = true

}