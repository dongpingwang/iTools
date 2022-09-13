package com.wdp.itools.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent

/**
 * author: Dongping Wang
 * date: 2022/9/13 23:03
 * description: ActivityUtils
 */
@SuppressLint("DiscouragedPrivateApi")
object ActivityUtils {

    fun startActivity(intent: Intent) {
        try {
            AppUtils.getContext().startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    fun startActivity(cls: Class<*>) {
        val intent = Intent(
            AppUtils.getContext(),
            cls
        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun startLauncherActivity(pkg: String) {
        AppUtils.getContext().packageManager.getLaunchIntentForPackage(pkg)?.apply {
            startActivity(this)
        }
    }

    fun isResumed(activity: Activity): Boolean {
        try {
            val mResumed = activity.javaClass.getDeclaredField("mResumed")
            mResumed.isAccessible = true
            return mResumed.getBoolean(activity)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
        return false
    }

    fun isStopped(activity: Activity): Boolean {
        try {
            val mStopped = activity.javaClass.getDeclaredField("mStopped")
            mStopped.isAccessible = true
            return mStopped.getBoolean(activity)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
        return false
    }

    fun isPaused(activity: Activity): Boolean = !isResumed(activity)
}