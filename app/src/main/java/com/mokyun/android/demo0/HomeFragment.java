package com.mokyun.android.demo0;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.eevoe.flow.FlowFragment;
import com.eevoe.flow.annotation.FlowBindView;
import com.eevoe.flow.annotation.FlowState;
import com.mokyun.android.demo0.state.LoginState;

@FlowBindView(view = R.layout.image, title = "测试")
public class HomeFragment extends FlowFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    @FlowState
    LoginState loginState;

    @Override
    public void initView(View view) {
        Log.wtf(TAG, "onCreateView: " + this.getClass().getSimpleName() );
        Log.wtf(TAG, "isLOGIN: " +loginState.isLogin());
        getNavBarVModel().rightOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(new LaunchFragment());
            }
        };

        view.findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getState().get(LoginState.class).setLogin(LoginState.NO_LOGIN);
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
            }
        });
        view.findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavBarVModel().setVisibility(View.GONE);
            }
        });
        view.findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavBarVModel().setVisibility(View.VISIBLE);
            }
        });
    }
}
