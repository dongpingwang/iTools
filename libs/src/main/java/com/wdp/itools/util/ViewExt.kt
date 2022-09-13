package com.wdp.itools.util

import android.widget.SeekBar

fun SeekBar.onSeekBarChange(listener: OnSeekBarChangeListener.() -> Unit) {
    OnSeekBarChangeListener().apply { listener() }.also { setOnSeekBarChangeListener(it) }
}

class OnSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {

    private var onProgressChanged: ((seekbar: SeekBar?, progress: Int, fromUser: Boolean) -> Unit)? = null
    private var onStartTrackingTouch: ((seekbar: SeekBar?) -> Unit)? = null
    private var onStopTrackingTouch: ((seekbar: SeekBar?) -> Unit)? = null

    override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
        onProgressChanged?.invoke(seekbar, progress, fromUser)
    }

    override fun onStartTrackingTouch(seekbar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekbar: SeekBar?) {}

    fun onProgressChanged(onProgressChanged: (seekbar: SeekBar?, progress: Int, fromUser: Boolean) -> Unit) {
        this.onProgressChanged = onProgressChanged
    }

    fun onStartTrackingTouch(onStartTrackingTouch: (seekbar: SeekBar?) -> Unit) {
        this.onStartTrackingTouch = onStartTrackingTouch
    }

    fun onStopTrackingTouch(onStopTrackingTouch: (seekbar: SeekBar?) -> Unit) {
        this.onStopTrackingTouch = onStopTrackingTouch
    }
}

