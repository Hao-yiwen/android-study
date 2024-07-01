package com.yiwen.java_view_other;

import java.util.concurrent.Callable;

class CallableTask implements Callable<String> {
    @Override
    public String call() {
        return "Callable Task Executed";
    }
}
