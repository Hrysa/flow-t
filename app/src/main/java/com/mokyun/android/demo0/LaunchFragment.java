package com.mokyun.android.demo0;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LaunchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.launch, container, false);
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
//                 getActivity().getSupportFragmentManager().popBackStack();
                HomeFragment fragment = new HomeFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.activity_container, fragment, HomeFragment.class.getSimpleName())
                        .commit();
             }
         });
         return view;
    }
}
