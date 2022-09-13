package com.wdp.itools.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * author: Dongping Wang
 * date: 2022/9/14 0:07
 * description: BitmapUtils
 */
object BitmapUtils {

    fun getBitmap(id: Int, opts: BitmapFactory.Options? = null): Bitmap? =
        ResUtils.getBitmap(id, opts)
}