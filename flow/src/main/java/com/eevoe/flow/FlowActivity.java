package com.eevoe.flow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


abstract public class FlowActivity extends AppCompatActivity {

    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ui translucent theme.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Fragment fragment = onCreateFlow(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    abstract protected Fragment onCreateFlow(Bundle savedInstanceState);
}

