package com.mokyun.android.demo0;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.eevoe.flow.FlowActivity;
import com.mokyun.android.demo0.state.LoginState;

public class MainActivity extends FlowActivity {

    @Override
    protected Fragment onCreateFlow(Bundle savedInstanceState) {
        LoginState loginState = getState().get(LoginState.class);

        if (!loginState.isLogin()) {
            Toast.makeText(this, "not login", Toast.LENGTH_SHORT).show();
            return new LaunchFragment();
        } else {
            return new HomeFragment();
        }
    }

    public int getContextViewId() {
        return R.id.demo0;
    }
}
