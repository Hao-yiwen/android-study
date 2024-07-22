package com.yiwen.java_view_other;

import com.yiwen.java_view_other.utils.WorkerThread;

class RunnableTask implements Runnable {
    @Override
    public void run() {
        WorkerThread wt = new WorkerThread();
        wt.start();
        wt.prepareHandler();
        if (wt.mHandler != null) {
            wt.mHandler.sendMessage(wt.mHandler.obtainMessage(0, "Hello World"));
        }
        System.out.println("Runnable Task Executed");
    }
}
