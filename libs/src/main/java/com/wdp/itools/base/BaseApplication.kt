package com.wdp.itools.base

import android.app.Application
import com.wdp.itools.util.AppUtils

/**
 * author: Dongping Wang
 * date: 2022/9/13 23:31
 * description: BaseApplication
 */
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}