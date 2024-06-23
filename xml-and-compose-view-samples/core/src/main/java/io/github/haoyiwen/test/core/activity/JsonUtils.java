package io.github.haoyiwen.test.core.activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonUtils {

    public static String getKeyByValue(JSONObject jsonObject, String value) {
        String key = null;
        try {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String currentKey = keys.next();
                if (jsonObject.getString(currentKey).equals(value)) {
                    key = currentKey;
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return key;
    }
}
