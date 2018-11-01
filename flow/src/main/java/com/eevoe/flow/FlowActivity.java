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

    private FragmentManager fm;

    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fm = getSupportFragmentManager();

        // init ui translucent theme.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

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
        Log.i(TAG, "onBackPressed: " + fm.getBackStackEntryCount());
        FlowFragment currentFragment = (FlowFragment) getSupportFragmentManager().findFragmentById(getContextViewId());
        if (currentFragment != null) {
            currentFragment.back();
        }
    }

    public void replace(FlowFragment fragment) {
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

    public void push(FlowFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public FlowState getState() {
        return ((FlowApplication) getApplication()).getState();
    }

    public void back() {
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    abstract protected Fragment onCreateFlow(Bundle savedInstanceState);

    abstract public int getContextViewId();
}

