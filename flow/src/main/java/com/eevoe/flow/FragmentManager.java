package com.eevoe.flow;

import android.app.Activity;
import android.app.ActivityManager;
import android.support.v4.app.Fragment;

import java.util.Stack;

public class FragmentManager implements ContextManager {
    private static Stack<Fragment> mStack;

    public Stack<Fragment> getStack() {
//        ActivityManager
        return mStack;
    }

}
