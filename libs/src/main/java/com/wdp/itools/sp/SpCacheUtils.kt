package com.wdp.itools.sp

import com.wdp.itools.util.AppUtils

/**
 * author: Dongping Wang
 * date: 2022/9/15 0:18
 * description: SpCacheUtils
 */
@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
class SpCacheUtils(name: String? = null) {

    companion object {
        private val caches = mutableMapOf<String, SpCacheUtils>()

        fun newInstance(name: String? = null): SpCacheUtils {
            synchronized(caches) {
                val spName = if (name.isNullOrEmpty()) AppUtils.getContext().packageName else name
                var spCacheUtils = caches[spName]
                if (spCacheUtils == null) {
                    spCacheUtils = SpCacheUtils(name)
                    caches[spName] = spCacheUtils
                }
                return spCacheUtils
            }
        }
    }

    private val values = mutableMapOf<String, Any?>()
    private val spUtils by lazy { SpUtils(name) }

    fun <T : Any> get(key: String, defValue: T?): T? {
        if (values.contains(key)) {
            return values[key] as T?
        }
        val value = when (defValue) {
            null -> spUtils.getString(key, defValue)
            is Int -> spUtils.getInt(key, defValue)
            is Float -> spUtils.getFloat(key, defValue)
            is String -> spUtils.getString(key, defValue)
            is Long -> spUtils.getLong(key, defValue)
            is Boolean -> spUtils.getBoolean(key, defValue)
            is Set<*> -> spUtils.getAll()
            else -> null
        }
        values[key] = value
        return value as T?
    }


    fun <T : Any> put(key: String, value: T?) {
        values[key] = value
        when (value) {
            null -> spUtils.putString(key, value)
            is Int -> spUtils.putInt(key, value)
            is Float -> spUtils.putFloat(key, value)
            is String -> spUtils.putString(key, value)
            is Long -> spUtils.putLong(key, value)
            is Boolean -> spUtils.putBoolean(key, value)
            is Set<*> -> spUtils.putStringSet(key, value as Set<String>)
        }
    }

    fun spUtils() = spUtils
}