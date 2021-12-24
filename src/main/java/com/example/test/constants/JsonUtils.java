package com.example.test.constants;


import com.google.gson.Gson;

public class JsonUtils {

    public static <T> String objToJsonStr(T obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
