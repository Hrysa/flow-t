package com.mokyun.android.demo0;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.eevoe.flow.FlowFragment;
import com.mokyun.android.demo0.state.LoginState;

public class LoginFragment extends FlowFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
        hideNav();
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.login, null, false);
//        view.findViewById(R.id.launch_icon).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                push(new HomeFragment());
//            }
//        });
//
//        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getState().get(LoginState.class).setLogin(LoginState.IS_LOGIN);
//            }
//        });
        return view;
    }
}
