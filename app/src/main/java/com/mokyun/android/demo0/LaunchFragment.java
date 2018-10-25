package com.mokyun.android.demo0;

import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eevoe.flow.FlowActivity;
import com.eevoe.flow.FlowFragment;

public class LaunchFragment extends FlowFragment {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
//         View view = inflater.inflate(R.layout.launch, container, false);
//         view.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
////                 getActivity().getSupportFragmentManager().popBackStack();
////                HomeFragment fragment = new HomeFragment();
////                getActivity().getSupportFragmentManager()
////                        .beginTransaction()
////                        .add(R.id.activity_container, fragment, HomeFragment.class.getSimpleName())
////                        .commit();
//                 Log.wtf("go", "go");
//             }
//         });
//         return view;
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
        hideNav();
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.launch, null, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(new HomeFragment());
            }
        });
        return view;
    }
}
