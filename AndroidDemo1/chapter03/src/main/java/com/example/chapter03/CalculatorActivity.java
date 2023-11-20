package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_result;

    // 第一个操作数
    private String firstNumber = "";

    // 运算符
    private String operator = "";

    // 第二个操作数
    private String secondNumber = "";

    // 当前计算结果
    private String result = "";

    // 显示的文本内容
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String inputText;
        if (v.getId() == R.id.btn_sqrt) {
            inputText = "√";
        } else {
            inputText = ((TextView) v).getText().toString();
        }
        if (v.getId() == R.id.btn_clear) {
            clear();
        } else if (v.getId() == R.id.btn_cancel) {
            // do something
        } else if (v.getId() == R.id.btn_divide || v.getId() == R.id.btn_minus || v.getId() == R.id.btn_multiply || v.getId() == R.id.btn_plus) {
            operator = inputText;
            refreshText(showText + operator);
        } else if (v.getId() == R.id.btn_reciprocal) {
            double reciprocal_result = 1 / Double.parseDouble(firstNumber);
            refreshOperate(String.valueOf(reciprocal_result));
            refreshText(showText + "/=" + result);
        } else if (v.getId() == R.id.btn_equal) {
            double calcResult = calculatorFour();
            refreshOperate(String.valueOf(calcResult));
            refreshText(showText + "=" + result);
        } else if (v.getId() == R.id.btn_sqrt) {
            double sqrt_result = Math.sqrt(Double.parseDouble(firstNumber));
            refreshOperate(String.valueOf(sqrt_result));
            refreshText(showText + "√=" + result);
        } else {
            // 上次的运算结果已经出来了
            if (!result.isEmpty() && operator.isEmpty()) {
                clear();
            }
            // 无操作符，继续拼接第一个操作数
            if (operator.isEmpty()) {
                firstNumber += inputText;
            } else {
                // 有操作符，拼接第二个操作数
                secondNumber += inputText;
            }
            if (showText.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showText + inputText);
            }
        }
    }

    // 加减乘除四则运算，返回运算结果
    private double calculatorFour() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
            case "-":
                return Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
            case "×":
                return Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
            default:
                return Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
        }
    }

    public void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }

    public void clear() {
        refreshText("");
        refreshOperate("");
    }

    private void refreshOperate(String new_result){
        result = new_result;
        firstNumber = result;
        secondNumber = "";
        operator = "";
    }
}