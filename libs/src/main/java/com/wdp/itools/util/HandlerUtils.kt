package com.wdp.itools.util

import android.os.*
import androidx.annotation.RequiresApi

/**
 * author: Dongping Wang
 * date: 2022/9/14 0:24
 * description: HandlerUtils
 */
object HandlerUtils {

    enum class Strategy {
        Main(),
        Sub
    }

    private const val SUB_HANDLER_THREAD_NAME = "subHandler"

    private val messageHandler = mutableSetOf<(Message) -> Unit>()

    private val subHandler by lazy {
        val handlerThread = HandlerThread(SUB_HANDLER_THREAD_NAME)
        handlerThread.start()
        Handler(handlerThread.looper, callback)
    }

    private val mainHandler by lazy { Handler(Looper.getMainLooper(), callback) }

    private val callback by lazy {
        Handler.Callback { message ->
            messageHandler.onEach {
                it.invoke(message)
            }
            true
        }
    }
    private var strategy = Strategy.Sub
    private var curHandler = subHandler

    fun setStrategy(strategy: Strategy) {
        if (strategy != this.strategy) {
            this.strategy = strategy
            curHandler = if (HandlerUtils.strategy == Strategy.Sub) subHandler else mainHandler
        }
    }

    fun post(r: Runnable): Boolean = curHandler.post(r)
    fun postDelayed(r: Runnable, delayMillis: Long) = curHandler.postDelayed(r, delayMillis)
    fun postAtFrontOfQueue(r: Runnable) = curHandler.postAtFrontOfQueue(r)
    fun postAtTime(r: Runnable, uptimeMillis: Long) = curHandler.postAtTime(r, uptimeMillis)
    fun removeCallbacks(r: Runnable) = curHandler.removeCallbacks(r)

    @RequiresApi(Build.VERSION_CODES.Q)
    fun hasCallbacks(r: Runnable) = curHandler.hasCallbacks(r)
    fun sendEmptyMessage(what: Int) = curHandler.sendEmptyMessage(what)
    fun sendEmptyMessageAtTime(what: Int, uptimeMillis: Long) =
        curHandler.sendEmptyMessageAtTime(what, uptimeMillis)

    fun sendEmptyMessageDelayed(what: Int, delayMillis: Long) =
        curHandler.sendEmptyMessageDelayed(what, delayMillis)

    fun sendMessage(message: Message) = curHandler.sendMessage(message)
    fun sendMessageAtTime(message: Message, uptimeMillis: Long) =
        curHandler.sendMessageAtTime(message, uptimeMillis)

    fun sendMessageAtFrontOfQueue(message: Message) = curHandler.sendMessageAtFrontOfQueue(message)
    fun sendMessageDelayed(message: Message, delayMillis: Long) =
        curHandler.sendMessageDelayed(message, delayMillis)

    fun registerMessageHandler(handler: (Message) -> Unit) = messageHandler.add(handler)
    fun unregisterMessageHandler(handler: (Message) -> Unit) = messageHandler.remove(handler)
}