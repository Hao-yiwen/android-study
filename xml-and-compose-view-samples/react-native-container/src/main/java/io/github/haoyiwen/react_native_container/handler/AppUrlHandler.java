package io.github.haoyiwen.react_native_container.handler;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import io.github.haoyiwen.test.core.router.URLHandler;

public class AppUrlHandler implements URLHandler {
    @Override
    public boolean canHandle(String url) {
        return url.contains("yiwen://app?");
    }

    @Override
    public void handle(Context context, String url) {
        Uri uri = Uri.parse(url);
        // 获取uri的参数ModuleName
        String moduleName = uri.getQueryParameter("moduleName");
        // 读取 JSON 文件
        String jsonString = loadJSONFromAssets(context,"yiwen_app_activities.json");
        if (jsonString != null && moduleName != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                String className = jsonObject.getString(moduleName);
                if(className == null){
                    Toast.makeText(context, "className not found", Toast.LENGTH_LONG).show();
                    return;
                }
                Class<?> clazz = Class.forName(className);
                Intent intent = new Intent(context, clazz);

                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(context, "activities.json not found", Toast.LENGTH_LONG).show();
        }
    }

    private String loadJSONFromAssets(Context context, String fileName){
        String json = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
