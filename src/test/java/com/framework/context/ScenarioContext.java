package com.framework.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static Map<String, Object> dataMap = new HashMap<>();

    public static void set(String key, Object value) {
        dataMap.put(key, value);
    }

    public static Object get(String key) {
        return dataMap.get(key);
    }

    public static String getAsString(String key) {
        Object value = dataMap.get(key);
        return value != null ? value.toString() : null;
    }

    public static void clear() {
        dataMap.clear();
    }
}

