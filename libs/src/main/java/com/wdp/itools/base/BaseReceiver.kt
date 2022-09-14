package com.wdp.itools.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.ArrayMap
import com.wdp.itools.util.withNonNull

/**
 * author: Dongping Wang
 * date: 2022/9/15 1:10
 * description: BaseReceiver
 */
abstract class BaseReceiver : BroadcastReceiver() {

    private val actionHandlers = ArrayMap<String, ((Intent) -> Unit)>()

    override fun onReceive(context: Context?, intent: Intent?) {
        withNonNull(context, intent) { ctx, i ->
            receive(ctx, i)
            actionHandlers[i.action]?.invoke(i)
        }
    }

    protected fun receive(context: Context, intent: Intent) {}

    protected fun addHandler(action: String, handler: (Intent) -> Unit) {
        actionHandlers[action] = handler
    }
}