package com.wdp.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wdp.itools.sp.SpCacheUtils
import com.wdp.itools.util.HandlerUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val spCacheUtils = SpCacheUtils.newInstance()
        var value = spCacheUtils.get("aa", "null")
        Log.e("wdp", "getAA: $value")
        spCacheUtils.put("aa", "aa")
        value = spCacheUtils.get("aa", "null")
        Log.e("wdp", "getAA: $value")


    }
}