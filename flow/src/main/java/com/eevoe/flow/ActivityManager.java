package com.eevoe.flow;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager implements ContextManager {

    private static Stack<Activity> mStack;

    public Stack<Activity> getStack() {
        return mStack;
    }
}
