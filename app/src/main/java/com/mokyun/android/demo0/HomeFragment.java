package com.mokyun.android.demo0;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eevoe.flow.FlowFragment;
import com.mokyun.android.demo0.state.LoginState;

public class HomeFragment extends FlowFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
//        getNavBarVModel().leftOnClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "点击返回键", Toast.LENGTH_SHORT).show();
//            }
//        };
        getNavBarVModel().title.set("测试");
        getNavBarVModel().rightOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(new LaunchFragment());
            }
        };
//        getNavBarVModel().leftIconVisibility.set(View.INVISIBLE);
//        getNavBarVModel().rightIconVisibility.set(View.INVISIBLE);
        getNavBarVModel().hideLeftIcon();
//        getNavBarVModel().hideRightIcon();

//        getNavBarVModel().rightTitle.set("更多");

        View view = inflater.inflate(R.layout.image, null, false);
        view.findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getState().get(LoginState.class).setLogin(LoginState.NO_LOGIN);
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
            }
        });
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
