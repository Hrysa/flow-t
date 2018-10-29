package com.eevoe.flow.http.response;

import com.eevoe.flow.util.Json;

public class Res<T> {
    Integer code;
    String msg;
    T data;

    public Res() {
    }

    public T getData() {
        return this.data;
    }

    public boolean isOk() {
        return this.code == 0;
    }

    public String getMsg() {
        return this.msg;
    }

    public String toJsonString() {
        return new Json().stringify(this);
    }
}
