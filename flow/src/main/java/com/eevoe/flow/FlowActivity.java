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
        System.out.println("按下了back键   onBackPressed()");
        Log.wtf("onBackPressed: ", Integer.toString(getSupportFragmentManager().getFragments().size()) );
        if (getSupportFragmentManager().getFragments().size() > 0) {
//            super.onBackPressed();
            back();
        } else {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
    }

    public void replace(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction()
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out, R.anim.slide_right_in, R.anim.slide_right_out)
//                .add(R.id.activity_container, fragment)
//                .addToBackStack(fragment.getClass().getSimpleName())
//                .commit();
        mIsReplaceFragmentIndex = getSupportFragmentManager().getBackStackEntryCount() - 1;
        Log.i(TAG, "replace: index: " + Integer.toString(mIsReplaceFragmentIndex));
        push(fragment);
//        FragmentManager fm = getSupportFragmentManager();
//        int size = fm.getFragments().size() - 1;
//        getSupportFragmentManager().beginTransaction()
//                .remove(fm.getFragments().get(size))
//                .commit();
    }

    public void updateReplaceStatus() {
        if (mIsReplaceFragmentIndex > 0) {
            // TODO replace fragment.
            mIsReplaceFragmentIndex = -1;

//            getSupportFragmentManager().popBackStack();
//            final Fragment fragment = getSupportFragmentManager().getFragments().get(mIsReplaceFragmentIndex);
//            getSupportFragmentManager().beginTransaction()
//                    .remove(fragment)
//                    .commit();
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    getSupportFragmentManager().beginTransaction()
//                            .remove(fragment)
//                            .commit();
//                }
//            }, 0);
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
        Log.i(TAG, "popBackStack: getSupportFragmentManager().getBackStackEntryCount() = " + getSupportFragmentManager().getBackStackEntryCount());
        FragmentManager fm = getSupportFragmentManager();
        for (int i = size; i > 0; i--) {
            fm.popBackStackImmediate();
        }
    }

//    public <T extends Object> T getState() {
//        return ((FlowApplication) getApplication()).getState(new TypeToken<T>(){}.getClass());
//    }
    public FlowState getState() {
        return ((FlowApplication) getApplication()).getState();
    }

    public void back() {
        back(1);
    }

    abstract protected Fragment onCreateFlow(Bundle savedInstanceState);

    abstract public int getContextViewId();
}

