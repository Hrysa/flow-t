package com.eevoe.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.reflect.TypeToken;

import java.util.Timer;
import java.util.TimerTask;


abstract public class FlowActivity extends AppCompatActivity {
    private static final String TAG = "FlowActivity";

    private int mIsReplaceFragmentIndex = -1;

    private FrameLayout mContainer;

    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ui translucent theme.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

//        setContentView(R.layout.activity_main);
        mContainer = new FrameLayout(this);
        mContainer.setId(getContextViewId());
        setContentView(mContainer);

        final Fragment fragment = onCreateFlow(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(getContextViewId(), fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }


    @Override
    public void onBackPressed() {
        System.out.println("按下了back键   onBackPressed()" + getSupportFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            finish();
            startActivity(home);
        }
    }

    public void replace(Fragment fragment) {
        // TODO not working right now.
        mIsReplaceFragmentIndex = getSupportFragmentManager().getBackStackEntryCount() - 1;
        Log.i(TAG, "replace: index: " + Integer.toString(mIsReplaceFragmentIndex));
        push(fragment);
    }

    public void updateReplaceStatus() {
        if (mIsReplaceFragmentIndex > 0) {
            // TODO replace fragment.
            mIsReplaceFragmentIndex = -1;
        }
    }

    public void push(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void back(int size) {
        for (int i = size; i > 0; i--) {
            onBackPressed();
        }
    }

    public FlowState getState() {
        return ((FlowApplication) getApplication()).getState();
    }

    public void back() {
        back(1);
    }

    abstract protected Fragment onCreateFlow(Bundle savedInstanceState);

    abstract public int getContextViewId();
}

