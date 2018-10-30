package com.mokyun.android.demo0;

import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eevoe.flow.FlowActivity;
import com.eevoe.flow.FlowFragment;
import com.mokyun.android.demo0.state.LoginState;

public class LaunchFragment extends FlowFragment {
    private static String TAG = LaunchFragment.class.getSimpleName();
    private int mStatus = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
//        hideNav();
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.launch, null, false);
        view.findViewById(R.id.launch_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(new HomeFragment());
            }
        });
        Log.wtf(TAG, "status: " + mStatus);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStatus == 0) {
                    mStatus = 1;
                    getNavBarVModel().setLeftIconVisibility(View.GONE);
                    view.setBackgroundColor(Color.parseColor("#000000"));
                } else {
                    mStatus = 0;
                    getNavBarVModel().setLeftIconVisibility(View.VISIBLE);
                    view.setBackgroundColor(Color.parseColor("#a7ccc5"));
                }
            }
        });

        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getState().get(LoginState.class).setLogin(LoginState.IS_LOGIN);
            }
        });
        return view;
    }
}
