package com.wdp.itools.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat
import kotlin.math.roundToInt

/**
 * author: Dongping Wang
 * date: 2022/9/13 23:33
 * description: ResUtils
 */
object ResUtils {

    private val resources by lazy { AppUtils.getContext().resources }

    fun getString(@StringRes id: Int) = resources.getString(id)

    fun getString(@StringRes id: Int, vararg formatArgs: Any) = resources.getString(id, formatArgs)

    fun getAssets() = resources.assets

    fun getBoolean(@BoolRes id: Int) = resources.getBoolean(id)

    fun getInteger(@IntegerRes id: Int) = resources.getInteger(id)

    fun getIntArray(@ArrayRes id: Int) = resources.getIntArray(id)

    fun getStringArray(@ArrayRes id: Int) = resources.getStringArray(id)

    fun getFloat(@DimenRes id: Int) = ResourcesCompat.getFloat(resources, id)

    fun getText(@StringRes id: Int) = resources.getText(id)

    fun getTextArray(@ArrayRes id: Int) = resources.getTextArray(id)

    @ColorInt
    fun getColor(@ColorRes id: Int) = resources.getColor(id, null)

    fun getColorStateList(@ColorRes id: Int) = resources.getColorStateList(id, null)

    fun getDimension(@DimenRes id: Int) = resources.getDimension(id)

    fun getDimensionPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

    fun getDimensionPixelOffset(@DimenRes id: Int) = resources.getDimensionPixelOffset(id)

    fun getDrawable(@DrawableRes id: Int) = ResourcesCompat.getDrawable(resources, id, null)

    fun getIdentifier(
        name: String,
        defType: String,
        defPackage: String = AppUtils.getContext().packageName
    ) = resources.getIdentifier(name, defType, defPackage)

    fun getXml(@XmlRes id: Int) = resources.getXml(id)

    fun getDisplayMetrics() = resources.displayMetrics

    fun dp2px(dp: Float): Int = (getDisplayMetrics().density * dp).roundToInt()

    fun px2dp(px: Int): Int = (px / getDisplayMetrics().density).roundToInt()

    fun getBitmap(id: Int, opts: BitmapFactory.Options? = null): Bitmap? {
        try {
            return BitmapFactory.decodeResource(resources, id, opts)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}