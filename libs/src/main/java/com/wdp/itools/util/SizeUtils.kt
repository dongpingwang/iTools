package com.wdp.itools.util

enum class TimeUnit(millis: Long) {
    MILLIS(1),
    SECOND(1000),
    MINUTE(60_000),
    HOUR(3600_000)
}

object SizeUtils {

    fun dp2px(dp: Float): Int = ResUtils.dp2px(dp)

    fun px2dp(px: Int): Int = ResUtils.px2dp(px)
}