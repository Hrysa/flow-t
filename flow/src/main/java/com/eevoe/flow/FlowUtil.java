package com.eevoe.flow;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;

public class FlowUtil {
    private static FlowUtil mInstance;

    public static FlowUtil getInstance() {
        if (mInstance == null) {
            mInstance = new FlowUtil();
        }
        return mInstance;
    }

    static public class Type<T> {
        public Class<T> getTClass() {
            return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }
}
