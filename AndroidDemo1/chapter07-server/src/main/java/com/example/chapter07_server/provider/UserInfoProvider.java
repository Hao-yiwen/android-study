package com.example.chapter07_server.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.chapter07_server.UserInfoContent;
import com.example.chapter07_server.databse.UserDBHelper;

public class UserInfoProvider extends ContentProvider {
    private UserDBHelper dbHelper = null;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int USERS = 1;
    private static final int USER = 2;

    static {
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES, "/user", USERS);
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES, "/user/#", USER);
    }

    @Override
    public boolean onCreate() {
        Log.d("ning", "UserInfoProvider onCreate");
        dbHelper = UserDBHelper.getInstance(getContext());
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("ning", "UserInfoProvider insert");
        if (URI_MATCHER.match(uri) == USERS) {
            SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
            writableDatabase.insert(UserDBHelper.TABLE_NAME, null, values);
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (URI_MATCHER.match(uri)) {
            case USERS:
                Log.d("ning", "UserInfoProvider delete USERS");
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                count = db1.delete(UserDBHelper.TABLE_NAME, selection, selectionArgs);
                db1.close();
                break;
            // 删除单行
            case USER:
                Log.d("ning", "UserInfoProvider delete USER");
                String id = uri.getLastPathSegment();
                SQLiteDatabase db2 = dbHelper.getWritableDatabase();
                count = db2.delete(UserDBHelper.TABLE_NAME, "_id=?", new String[]{id});
                db2.close();
                break;
        }
        Log.d("ning", "UserInfoProvider delete");
        return count;
    }

    @Override
    public String getType(Uri uri) {
        Log.d("ning", "UserInfoProvider getType");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("ning", "UserInfoProvider query");
        if (URI_MATCHER.match(uri) == USERS) {
            SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
            return readableDatabase.query(UserDBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}