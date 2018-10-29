package com.eevoe.flow.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

public class Json {
    Gson gson;

    public Json() {
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        this.gson = gb.create();
    }

    public Json(boolean prettyMode) {
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        gb.setPrettyPrinting();
        this.gson = gb.create();
    }

    public <T> String stringify(T tObject) {
        return this.gson.toJson(tObject);
    }

    public <T> T parse(String str, Type typeOfT) {
        return this.gson.fromJson(str, typeOfT);
    }

    public <T> T parse(String str, Class<T> tClass) {
        return this.parse(str, (Type)tClass);
    }
}
