package com.wdp.itools.sp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.wdp.itools.util.AppUtils
import com.wdp.itools.util.addIfNotExist


/**
 * author: Dongping Wang
 * date: 2022/9/14 23:04
 * description: SharedPUtils
 */
class SpUtils(name: String? = null) {

    companion object {
        private val caches = mutableMapOf<String, SpUtils>()

        fun newInstance(name: String? = null): SpUtils {
            synchronized(caches) {
                val spName = if (name.isNullOrEmpty()) AppUtils.getContext().packageName else name
                var spUtils = caches[spName]
                if (spUtils == null) {
                    spUtils = SpUtils(name)
                    caches[spName] = spUtils
                }
                return spUtils
            }
        }
    }

    private val sp by lazy {
        val spName = if (name.isNullOrEmpty()) AppUtils.getContext().packageName else name
        AppUtils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    private val onDataChangeListeners by lazy { mutableListOf<(SpUtils, String) -> Unit>() }

    fun getBoolean(key: String, defValue: Boolean = false) = sp.getBoolean(key, defValue)

    fun getString(key: String, defValue: String? = null) = sp.getString(key, defValue)

    fun getInt(key: String, defValue: Int = 0) = sp.getInt(key, defValue)

    fun getStringSet(key: String, defValue: Set<String>? = null) = sp.getStringSet(key, defValue)

    fun getLong(key: String, defValue: Long = 0L) = sp.getLong(key, defValue)

    fun getFloat(key: String, defValue: Float = 0F) = sp.getFloat(key, defValue)

    fun getAll() = sp.all

    fun contains(key: String) = sp.contains(key)

    fun getEditor() = sp.edit()

    fun edit() = getEditor()

    fun edit(commit: Boolean = false, action: SharedPreferences.Editor.() -> Unit) = sp.edit(commit, action)

    fun put(key: String, value: String, commit: Boolean = false) = putString(key, value, commit)

    fun put(key: String, value: Boolean, commit: Boolean = false) = putBoolean(key, value, commit)

    fun put(key: String, value: Set<String>?, commit: Boolean = false) = putStringSet(key, value, commit)

    fun put(key: String, value: Int, commit: Boolean = false) = putInt(key, value, commit)

    fun put(key: String, value: Float, commit: Boolean = false) = putFloat(key, value, commit)

    fun put(key: String, value: Long, commit: Boolean = false) = putLong(key, value, commit)

    fun putBoolean(key: String, value: Boolean, commit: Boolean = false) = edit(commit) { putBoolean(key, value) }

    fun putString(key: String, value: String?, commit: Boolean = false) = edit(commit) { putString(key, value) }

    fun putStringSet(key: String, value: Set<String>?, commit: Boolean = false) = edit(commit) { putStringSet(key, value) }

    fun putInt(key: String, value: Int, commit: Boolean = false) = edit(commit) { putInt(key, value) }

    fun putFloat(key: String, value: Float, commit: Boolean = false) = edit(commit) { putFloat(key, value) }

    fun putLong(key: String, value: Long, commit: Boolean = false) = edit(commit) { putLong(key, value) }

    fun clear() = edit().clear()

    fun remove(key: String) = edit().remove(key)

    fun registerOnDataChangeListener(listener: (SpUtils, String) -> Unit): Boolean {
        if (onDataChangeListeners.isEmpty()) {
            sp.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
        }
        return onDataChangeListeners.addIfNotExist(listener)
    }

    fun unregisterOnDataChangeListener(listener: (SpUtils, String) -> Unit): Boolean {
        val result = onDataChangeListeners.remove(listener)
        if (onDataChangeListeners.isEmpty()) {
            sp.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
        }
        return result
    }

    private val onSharedPreferenceChangeListener by lazy {
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            onDataChangeListeners.onEach {
                it.invoke(this@SpUtils, key)
            }
        }
    }
}