package com.eevoe.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


abstract public class FlowActivity extends AppCompatActivity {

    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ui translucent theme.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setContentView(R.layout.activity_main);

        final Fragment fragment = onCreateFlow(savedInstanceState);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.activity_container, fragment)
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commit();
            }
        }, 2000);

    }


    @Override
    public void onBackPressed() {
        System.out.println("按下了back键   onBackPressed()");
        Log.wtf("onBackPressed: ", Integer.toString(getSupportFragmentManager().getFragments().size()) );
        if (getSupportFragmentManager().getFragments().size() > 1) {
            super.onBackPressed();
        } else {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
    }

    public void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out, R.anim.slide_right_in, R.anim.slide_right_out)
                .replace(R.id.activity_container, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void push(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out, R.anim.slide_right_in, R.anim.slide_right_out)
                .add(R.id.activity_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void back(int size) {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = size; i > 0; i--) {
            fm.popBackStack();
        }
    }

    public void back() {
        back(1);
    }

    abstract protected Fragment onCreateFlow(Bundle savedInstanceState);
}

