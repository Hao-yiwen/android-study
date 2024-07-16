package com.yiwen.java_view_other.delegate;

public class Downlodaer {
    private DownloadListener listener;

    public Downlodaer(DownloadListener listener) {
        this.listener = listener;
    }

    public void download() {
        String result = "下载完成";

        if(listener != null){
            listener.onDownloadComplete(result);
        }
    }
}
