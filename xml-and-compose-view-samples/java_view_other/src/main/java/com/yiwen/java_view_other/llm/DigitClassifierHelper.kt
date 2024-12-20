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
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.SystemClock
import android.util.Log
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp
import org.tensorflow.lite.support.image.ops.TransformToGrayscaleOp
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import java.nio.ByteBuffer

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

    fun classify(originalBitmap: Bitmap) {
        if (digitClassifier == null) {
            setupDigitClassifier()
        }

        try {
            // 1. 缩放到 28x28
            val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 28, 28, true)

            // 2. 转换为反色灰度图，因为 MNIST 数据集是黑底白字
            val grayBitmap = Bitmap.createBitmap(28, 28, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(grayBitmap)
            val paint = Paint().apply {
                colorFilter = ColorMatrixColorFilter(ColorMatrix().apply {
                    val matrix = floatArrayOf(
                        -0.299f, -0.587f, -0.114f, 0f, 255f,  // 反色并转灰度
                        -0.299f, -0.587f, -0.114f, 0f, 255f,
                        -0.299f, -0.587f, -0.114f, 0f, 255f,
                        0f, 0f, 0f, 1f, 0f
                    )
                    set(matrix)
                })
            }
            canvas.drawBitmap(scaledBitmap, 0f, 0f, paint)

            // 调试：保存处理后的图像进行检查
            Log.d(TAG, "Intermediate bitmap debug info:")
            val pixels = IntArray(28 * 28)
            grayBitmap.getPixels(pixels, 0, 28, 0, 0, 28, 28)
            val pixelValues = pixels.take(5).joinToString {
                String.format("R:%d G:%d B:%d",
                    Color.red(it), Color.green(it), Color.blue(it))
            }
            Log.d(TAG, "First 5 pixels: $pixelValues")

            // 3. 转换为 TensorImage
            val tensorImage = TensorImage.fromBitmap(grayBitmap)
            Log.d(TAG, "Tensor shape before processing: ${tensorImage.tensorBuffer.shape.contentToString()}")
            Log.d(TAG, "Tensor values: ${tensorImage.tensorBuffer.floatArray.take(10)}")

            // 4. 图像预处理
            val imageProcessor = ImageProcessor.Builder()
                .add(NormalizeOp(0f, 255f))  // 归一化到 [0,1]
                .build()

            val processedImage = imageProcessor.process(tensorImage)
            Log.d(TAG, "Processed shape: ${processedImage.tensorBuffer.shape.contentToString()}")
            Log.d(TAG, "Processed values: ${processedImage.tensorBuffer.floatArray.take(10)}")
            // 5. 执行推理
            var inferenceTime = SystemClock.uptimeMillis()
            val results = digitClassifier?.classify(processedImage)
            inferenceTime = SystemClock.uptimeMillis() - inferenceTime

            Log.d(TAG, "Raw results: $results")
            results?.forEach { classifications ->
                Log.d(TAG, "Head index: ${classifications.headIndex}")
                classifications.categories.forEach { category ->
                    Log.d(TAG, "Label: ${category.label}, Score: ${category.score}")
                }
            }

            digitClassifierListener?.onResults(results, inferenceTime)

            // 清理
            scaledBitmap.recycle()
            grayBitmap.recycle()

        } catch (e: Exception) {
            Log.e(TAG, "Classification error", e)
            e.printStackTrace()
            digitClassifierListener?.onError("分类错误: ${e.message}")
        }
    }

    private fun setupDigitClassifier() {
        try {
            // 检查模型文件
            context.assets.open("mnist.tflite").use {
                Log.d(TAG, "Model file size: ${it.available()} bytes")
            }

            val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
                .setScoreThreshold(0.1f)  // 降低阈值到 0.1
                .setMaxResults(1)         // 增加返回结果数

            val baseOptionsBuilder = BaseOptions.builder()
                .setNumThreads(2)

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

            Log.d(TAG, "Model loaded successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Model setup error", e)
            e.printStackTrace()
            digitClassifierListener?.onError("模型初始化失败: ${e.message}")
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

