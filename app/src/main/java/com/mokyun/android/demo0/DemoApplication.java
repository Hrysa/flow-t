package com.mokyun.android.demo0;

import com.eevoe.flow.FlowApplication;
import com.mokyun.android.demo0.state.ACacheState;
import com.mokyun.android.demo0.state.LoginState;


public class DemoApplication extends FlowApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        getState().add(new ACacheState(getApplicationContext()));
        getState().add(new LoginState());
    }
}
