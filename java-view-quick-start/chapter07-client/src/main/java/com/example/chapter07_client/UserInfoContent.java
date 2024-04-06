package com.example.chapter07_client;

import android.net.Uri;

public class UserInfoContent {
    public static final String AUTHORITIES = "com.example.chapter07_server.provider.UserInfoProvider";

    // content://com.example.chapter07_server.provider.UserInfoProvider/user

    // 翻个问你内容提供器的URI
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/user");

    // 下面是该表的各个字段名称
    public static final String USER_NAME = "name";
    public static final String USER_AGE = "age";
    public static final String USER_HEIGHT = "height";
    public static final String USER_WEIGHT = "weight";
    public static final String USER_MARRIED = "married";

    public static final String USER_UPDATE_TIME = "update_time";
}
