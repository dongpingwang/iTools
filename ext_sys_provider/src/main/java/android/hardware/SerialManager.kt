package android.hardware

import java.nio.ByteBuffer

class SerialPort {

    fun getName(): String? = null
    fun read(buffer: ByteBuffer): Int = 0
    fun write(buffer: ByteBuffer, length: Int) {}
    fun sendBreak() {}
    fun open() {}
    fun close() {}
}

class SerialManager {
    fun getSerialPorts(): Array<String>? = null

    fun openSerialPort(name: String, speed: Int): SerialPort? = null

}