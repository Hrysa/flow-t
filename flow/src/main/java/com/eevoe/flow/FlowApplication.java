package com.eevoe.flow;

import android.app.Application;


abstract public class FlowApplication extends Application {

    private FlowState mFlowState;

    @Override
    public void onCreate() {
        super.onCreate();
        mFlowState = new FlowState();
    }
    public FlowState getState() {
        return mFlowState;
    }
}

