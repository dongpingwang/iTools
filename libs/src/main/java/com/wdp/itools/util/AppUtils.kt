package com.wdp.itools.util

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * author: Dongping Wang
 * date: 2022/9/9 21:28
 * description: AppUtils
 */
object AppUtils {

    private var app by Delegates.notNull<Application>()

    fun init(application: Application) {
        this.app = application
    }

    fun getApplicationContext(): Context {
        return app.applicationContext
    }

    fun getApplication(): Application {
        return app
    }

    fun getContext(): Context {
        return getApplicationContext()
    }
}