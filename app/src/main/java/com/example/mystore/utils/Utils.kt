package com.example.mystore.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class Utils {
    companion object {
        fun readRawFile(context: Context,id: Int): String {
            val raw = context.resources.openRawResource(id)
            val bufReader = BufferedReader(InputStreamReader(raw))
            val stringBuilder = StringBuilder()
            bufReader.lines().forEach {
                stringBuilder.append(it)
            }
            bufReader.close()

            return stringBuilder.toString()
        }
    }
}