package com.wdp.itools.sys

import android.annotation.SuppressLint
import android.hardware.SerialManager
import android.hardware.SerialPort
import com.wdp.itools.util.AppUtils
import java.nio.ByteBuffer

/**
 * author: Dongping Wang
 * date: 2022/9/19 22:58
 * description: SerialManager
 */
class SerialManager {

    private val dataBufferSize = 1024

    @SuppressLint("WrongConstant")
    private val serialManager = AppUtils.getContext().getSystemService("serial") as SerialManager
    private var inputBuffer = ByteBuffer.allocate(dataBufferSize)
    private var outBuffer = ByteBuffer.allocate(dataBufferSize)
    private var serialPort: SerialPort? = null
    private var buffer = ByteArray(dataBufferSize)
    private var onDataReceiveListener: ((ByteArray) -> Unit)? = null

    fun openSerialPort(name: String, speed: Int): Boolean {
        try {
            serialPort = serialManager.openSerialPort(name, speed)
            val result = serialPort == null
            if (result) {
                ReaderThread().start()
            }
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun closeSerialPort() {
        serialPort?.apply {
            try {
                close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        serialPort = null
    }

    fun writeData(data: ByteArray) {
        if (data.isEmpty()) {
            return
        }
        if (data.size > dataBufferSize) {
            outBuffer = ByteBuffer.allocate(data.size)
        }
        serialPort?.apply {
            outBuffer.clear()
            outBuffer.put(data)
            write(outBuffer, data.size)
        }
    }

    fun setOnDataReceiveListener(onDataReceiveListener: (ByteArray) -> Unit) {
        this.onDataReceiveListener = onDataReceiveListener
    }

    private inner class ReaderThread : Runnable {

        fun start() {
            Thread(this).start()
        }

        override fun run() {
            var ret = 0
            while (ret >= 0) {
                serialPort?.apply {
                    try {
                        inputBuffer.clear()
                        ret = read(inputBuffer)
                        inputBuffer.get(buffer, 0, ret)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                if (ret > 0) {
                    onDataReceiveListener?.invoke(buffer)
                }
            }
        }
    }


}