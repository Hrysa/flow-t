package com.mokyun.android.demo0;


import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.eevoe.flow.FlowActivity;

public class MainActivity extends FlowActivity {

    @Override
    protected Fragment onCreateFlow(Bundle savedInstanceState) {
        return new LaunchFragment();
    }
}
