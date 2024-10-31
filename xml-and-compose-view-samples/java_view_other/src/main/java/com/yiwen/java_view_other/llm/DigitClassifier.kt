package com.yiwen.java_view_other.llm

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class DigitClassifier(private val context: Context) {
    private var interpreter: Interpreter? = null

}