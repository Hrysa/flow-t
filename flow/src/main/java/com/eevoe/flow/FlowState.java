package com.eevoe.flow;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

public class FlowState {

    private FlowState mFlowState;
    private HashMap<Class, Object> mStatePool;

    public FlowState() {
        mStatePool = new HashMap<>();
        mFlowState = this;
    }

    public FlowState getInstance() {
        return mFlowState;
    }

    public<T extends FlowStateInterFace> FlowState add(T o) {
        Class className = o.getClass();
        if (mStatePool.get(className) == null) {
            o.init(this);
            mStatePool.put(className, o);
        }
        return this;
    }

    public <T extends FlowStateInterFace> T get(Class<T> clazz) {
        T r = (T) mStatePool.get(clazz);
        return r;
    }
}
