package com.mokyun.android.demo0;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.eevoe.flow.FlowFragment;

public class HomeFragment extends FlowFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.image, null, false);
        Button b1 = view.findViewById(R.id.hide);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideNav();
            }
        });
        Button b2 = view.findViewById(R.id.show);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNav();
            }
        });
        return view;
    }
}
