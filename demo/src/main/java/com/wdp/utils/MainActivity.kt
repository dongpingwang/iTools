package com.wdp.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wdp.itools.log.Logger
import com.wdp.itools.sp.SpCacheUtils
import com.wdp.itools.util.HandlerUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.json("wdp","{ \"firstName\": \"Brett\", \"lastName\":\"McLaughlin\", \"email\": \"brett@newInstance.com\" }")

        Logger.xml("wdp","<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
                "    package=\"com.wdp.utils\">\n" +
                "\n" +
                "    <application\n" +
                "        android:allowBackup=\"true\"\n" +
                "        android:dataExtractionRules=\"@xml/data_extraction_rules\"\n" +
                "        android:fullBackupContent=\"@xml/backup_rules\"\n" +
                "        android:icon=\"@mipmap/ic_launcher\"\n" +
                "        android:label=\"@string/app_name\"\n" +
                "        android:roundIcon=\"@mipmap/ic_launcher_round\"\n" +
                "        android:supportsRtl=\"true\"\n" +
                "        android:theme=\"@style/Theme.Utils\"\n" +
                "        android:name=\".App\"\n" +
                "        tools:targetApi=\"31\">\n" +
                "        <activity\n" +
                "            android:name=\".MainActivity\"\n" +
                "            android:exported=\"true\">\n" +
                "            <intent-filter>\n" +
                "                <action android:name=\"android.intent.action.MAIN\" />\n" +
                "\n" +
                "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
                "            </intent-filter>\n" +
                "        </activity>\n" +
                "    </application>\n" +
                "\n" +
                "</manifest>")

        Logger.e("wdp","------------------------------",Throwable("hhhhhh"))

    }
}