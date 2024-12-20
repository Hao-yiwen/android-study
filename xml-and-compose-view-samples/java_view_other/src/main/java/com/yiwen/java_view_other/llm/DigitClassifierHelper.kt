/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tensorflow.lite.examples.digitclassification

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.SystemClock
import android.util.Log
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class DigitClassifierHelper(
    var threshold: Float = 0.5f,
    var numThreads: Int = 2,
    var maxResults: Int = 3,
    var currentDelegate: Int = 0,
    val context: Context,
    val digitClassifierListener: DigitClassifierListener?
) {
    private var digitClassifier: ImageClassifier? = null

    init {
        setupDigitClassifier()
    }

    // 在类内部或作为伴生对象定义接口
    interface DigitClassifierListener {
        fun onError(error: String)
        fun onResults(
            results: List<Classifications>?,
            inferenceTime: Long
        )
    }

    private fun setupDigitClassifier() {
        try {
            val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
                .setScoreThreshold(threshold)
                .setMaxResults(maxResults)

            val baseOptionsBuilder = BaseOptions.builder()
                .setNumThreads(numThreads)

            when (currentDelegate) {
                DELEGATE_CPU -> {
                    // 使用默认 CPU
                }
                DELEGATE_GPU -> {
                    if (CompatibilityList().isDelegateSupportedOnThisDevice) {
                        baseOptionsBuilder.useGpu()
                    }
                }
                DELEGATE_NNAPI -> {
                    baseOptionsBuilder.useNnapi()
                }
            }

            optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

            digitClassifier = ImageClassifier.createFromFileAndOptions(
                context,
                "mnist.tflite",
                optionsBuilder.build()
            )
        } catch (e: IllegalStateException) {
            digitClassifierListener?.onError("模型初始化失败: ${e.message}")
            Log.e(TAG, "TFLite failed to load model with error: ${e.message}")
        }
    }

    fun classify(originalBitmap: Bitmap) {
        if (digitClassifier == null) {
            setupDigitClassifier()
        }

        // 预处理图片为 MNIST 格式 (28x28)
        val processedBitmap = Bitmap.createScaledBitmap(originalBitmap, 28, 28, true)

        // 转换为灰度图
        val pixels = IntArray(28 * 28)
        processedBitmap.getPixels(pixels, 0, 28, 0, 0, 28, 28)
        val grayBitmap = Bitmap.createBitmap(28, 28, Bitmap.Config.ARGB_8888)

        for (i in pixels.indices) {
            val pixel = pixels[i]
            val gray = (Color.red(pixel) * 0.299f +
                    Color.green(pixel) * 0.587f +
                    Color.blue(pixel) * 0.114f).toInt()
            pixels[i] = Color.rgb(gray, gray, gray)
        }
        grayBitmap.setPixels(pixels, 0, 28, 0, 0, 28, 28)

        try {
            var inferenceTime = SystemClock.uptimeMillis()

            // 转换为 TensorImage 并添加 batch 维度
            val tensorImage = TensorImage.fromBitmap(grayBitmap)

            // 添加图像预处理
            val imageProcessor = ImageProcessor.Builder()
                .add(ResizeWithCropOrPadOp(28, 28))  // 确保尺寸
                .add(NormalizeOp(0f, 255f))  // 归一化像素值
                .build()

            val processedImage = imageProcessor.process(tensorImage)

            val results = digitClassifier?.classify(processedImage)
            inferenceTime = SystemClock.uptimeMillis() - inferenceTime
            digitClassifierListener?.onResults(results, inferenceTime)
            Log.d(TAG, "Inference time: $inferenceTime ms")

        } catch (e: Exception) {
            digitClassifierListener?.onError("分类错误: ${e.message}")
        } finally {
            processedBitmap.recycle()
            grayBitmap.recycle()
        }
    }

    fun clearDigitClassifier() {
        digitClassifier = null
    }

    companion object {
        const val DELEGATE_CPU = 0
        const val DELEGATE_GPU = 1
        const val DELEGATE_NNAPI = 2
        private const val TAG = "DigitClassifierHelper"
    }
}

