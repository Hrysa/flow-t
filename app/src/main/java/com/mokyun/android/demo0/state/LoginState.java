package com.mokyun.android.demo0.state;

import android.util.Log;

import com.eevoe.flow.FlowState;
import com.eevoe.flow.FlowStateInterFace;
import com.eevoe.flow.util.ACache;

public class LoginState implements FlowStateInterFace {

    public static boolean IS_LOGIN = true;
    public static boolean NO_LOGIN = false;

    public ACache mACache;


    private boolean mIsLogin = false;

    public boolean isLogin() {
        return mIsLogin;
    }

    public void init(FlowState flowState) {
        mACache = ((ACacheState) flowState.get(ACacheState.class)).get();


        Boolean isLogin = mACache.get(getClass().getName());
        if (isLogin != null && isLogin == IS_LOGIN) {
            mIsLogin = true;
        }
    }

    public void setLogin(boolean s) {
        mIsLogin = s;
        Log.wtf("setLogin: ", getClass().getName());
        mACache.put(getClass().getName(), mIsLogin);
    }
}
