package com.mokyun.android.demo0;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eevoe.flow.FlowFragment;

public class HomeFragment extends FlowFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
        getNavBarVModel().setLeftTitle("毛脚");
//        getNavBarVModel().leftOnClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "点击返回键", Toast.LENGTH_SHORT).show();
//            }
//        };
        getNavBarVModel().setTitle("测试页面");
        getNavBarVModel().rightOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(new HomeFragment());
            }
        };
        getNavBarVModel().rightTitle.set("更多");

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
