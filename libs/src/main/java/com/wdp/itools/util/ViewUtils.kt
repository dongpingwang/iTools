package com.wdp.itools.util

/**
 * author: Dongping Wang
 * date: 2022/9/16 0:34
 * description: ViewUtils
 */
object ViewUtils {

    private const val FAST_CLICK_INTERVAL_MS = 200L

    private var lastClickMillis = 0L

    fun isFastClick(): Boolean {
        val currentTimeMillis = System.currentTimeMillis()
        return (lastClickMillis + FAST_CLICK_INTERVAL_MS >= currentTimeMillis).also {
            lastClickMillis = currentTimeMillis
        }
    }
}