package com.mokyun.android.demo0;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.eevoe.flow.FlowFragment;
import com.eevoe.flow.http.response.Res;
import com.mokyun.android.demo0.http.Post;
import com.mokyun.android.demo0.http.TestService;
import com.mokyun.android.demo0.state.ApiClientState;

import junit.framework.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends FlowFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
        hideNav();
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.login, null, false);
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(new LaunchFragment());
            }
        });
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
        getState()
                .get(ApiClientState.class)
                .create(TestService.class)
                .test()
                .enqueue(new Callback<Res<List<Post>>>() {
                    @Override
                    public void onResponse(Call<Res<List<Post>>> call, Response<Res<List<Post>>> response) {
                        Log.wtf("????", response.body().toJsonString());
                        response.body();
                    }

                    @Override
                    public void onFailure(Call<Res<List<Post>>> call, Throwable t) {}
                });
        return view;
    }
}
