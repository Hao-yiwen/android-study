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

package com.yiwen.java_view_other.fragemnt

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yiwen.java_view_other.R
import com.yiwen.java_view_other.databinding.FragmentDigitCanvasBinding
import org.tensorflow.lite.examples.digitclassification.DigitClassifierHelper
import org.tensorflow.lite.examples.digitclassification.fragments.ClassificationResultsAdapter
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.util.Locale

class DigitCanvasFragment : Fragment(), DigitClassifierHelper.DigitClassifierListener {
    private var _fragmentDigitCanvasBinding: FragmentDigitCanvasBinding? = null
    private val fragmentDigitCanvasBinding get() = _fragmentDigitCanvasBinding!!

    // 使用 lazy 替代 lateinit，确保安全初始化
    private val classificationResultAdapter by lazy {
        ClassificationResultsAdapter()
    }

    private var digitClassifierHelper: DigitClassifierHelper? = null

    @SuppressLint("ClickableViewAccessibility")
    private fun setupDigitCanvas() {
        with(fragmentDigitCanvasBinding.digitCanvas) {
            setStrokeWidth(70f)
            setColor(Color.WHITE)
            setBackgroundColor(Color.BLACK)
            setOnTouchListener { _, event ->
                // As we have interrupted DrawView's touch event, we first
                // need to pass touch events through to the instance for the drawing to show up
                onTouchEvent(event)

                // Then if user finished a touch event, run classification
                if (event.action == MotionEvent.ACTION_UP) {
                    classifyDrawing()
                }
                true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            // 1. 先初始化 UI 组件
            setupClassificationResultAdapter()
            setupDigitCanvas()

            // 2. 再初始化分类器
            digitClassifierHelper = DigitClassifierHelper(
                context = requireContext(),
                digitClassifierListener = this
            )

            // 3. 最后设置控制器
            initBottomSheetControls()
            setupClearButton()
        } catch (e: Exception) {
            handleError("初始化失败: ${e.message}")
        }
    }

    private fun setupClearButton() {
        fragmentDigitCanvasBinding.btnClear.setOnClickListener {
            fragmentDigitCanvasBinding.digitCanvas.clearCanvas()
            classificationResultAdapter.reset()
        }
    }

    private fun setupClassificationResultAdapter() {
        with(fragmentDigitCanvasBinding.recyclerViewResults) {
            adapter = classificationResultAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun classifyDrawing() {
        try {
            val bitmap = fragmentDigitCanvasBinding.digitCanvas.getBitmap()
            digitClassifierHelper?.classify(bitmap)
        } catch (e: Exception) {
            handleError("分类失败: ${e.message}")
        }
    }

    override fun onError(error: String) {
        // 使用扩展函数简化错误处理
        handleError(error)
    }

    private fun handleError(error: String) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            try {
                classificationResultAdapter.reset()
            } catch (e: Exception) {
                Log.e(TAG, "Error resetting adapter: ${e.message}")
            }
        }
    }

    override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
        activity?.runOnUiThread {
            try {
                classificationResultAdapter.updateResults(results)
                fragmentDigitCanvasBinding.tvInferenceTime.text =
                    getString(R.string.inference_time, inferenceTime.toString())
            } catch (e: Exception) {
                handleError("处理结果失败: ${e.message}")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentDigitCanvasBinding = FragmentDigitCanvasBinding.inflate(
            inflater,
            container,
            false
        )
        return fragmentDigitCanvasBinding.root
    }

    override fun onDestroyView() {
        _fragmentDigitCanvasBinding = null
        super.onDestroyView()
    }

    private fun initBottomSheetControls() {
        // 使用安全调用操作符 ?.
        fragmentDigitCanvasBinding.bottomSheetLayout.thresholdMinus.setOnClickListener {
            digitClassifierHelper?.let { classifier ->
                if (classifier.threshold >= 0.1) {
                    classifier.threshold -= 0.1f
                    updateControlsUi()
                }
            }
        }

        fragmentDigitCanvasBinding.bottomSheetLayout.thresholdPlus.setOnClickListener {
            digitClassifierHelper?.let { classifier ->
                if (classifier.threshold < 0.9) {
                    classifier.threshold += 0.1f
                    updateControlsUi()
                }
            }
        }

        fragmentDigitCanvasBinding.bottomSheetLayout.maxResultsMinus.setOnClickListener {
            digitClassifierHelper?.let { classifier ->
                if (classifier.maxResults > 1) {
                    classifier.maxResults--
                    updateControlsUi()
                }
            }
        }

        fragmentDigitCanvasBinding.bottomSheetLayout.maxResultsPlus.setOnClickListener {
            digitClassifierHelper?.let { classifier ->
                if (classifier.maxResults < 3) {
                    classifier.maxResults++
                    updateControlsUi()
                }
            }
        }

        fragmentDigitCanvasBinding.bottomSheetLayout.threadsMinus.setOnClickListener {
            digitClassifierHelper?.let { classifier ->
                if (classifier.numThreads > 1) {
                    classifier.numThreads--
                    updateControlsUi()
                }
            }
        }

        fragmentDigitCanvasBinding.bottomSheetLayout.threadsPlus.setOnClickListener {
            digitClassifierHelper?.let { classifier ->
                if (classifier.numThreads < 4) {
                    classifier.numThreads++
                    updateControlsUi()
                }
            }
        }

        fragmentDigitCanvasBinding.bottomSheetLayout.spinnerDelegate.setSelection(0, false)
        fragmentDigitCanvasBinding.bottomSheetLayout.spinnerDelegate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    digitClassifierHelper?.currentDelegate = position
                    updateControlsUi()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    /* no op */
                }
            }
    }

    private fun updateControlsUi() {
        digitClassifierHelper?.let { classifier ->
            fragmentDigitCanvasBinding.bottomSheetLayout.apply {
                maxResultsValue.text = classifier.maxResults.toString()
                thresholdValue.text = String.format(Locale.US, "%.2f", classifier.threshold)
                threadsValue.text = classifier.numThreads.toString()
            }
            classifier.clearDigitClassifier()
        }
    }

    companion object {
        private const val TAG = "DigitCanvasFragment"
    }
}