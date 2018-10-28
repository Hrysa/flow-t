package com.mokyun.android.demo0.state;

import android.content.Context;

import com.eevoe.flow.FlowState;
import com.eevoe.flow.FlowStateInterFace;
import com.eevoe.flow.util.ACache;

public class ACacheState implements FlowStateInterFace {

    ACache mCache;

    public ACacheState(Context ctx) {
        mCache = ACache.get(ctx);
    }
    @Override
    public void init(FlowState flowState) {
    }

    public ACache get() {
        return mCache;
    }
}
