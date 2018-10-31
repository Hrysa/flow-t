package com.mokyun.android.demo0;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.eevoe.flow.FlowFragment;
import com.eevoe.flow.annotation.FlowBindView;
import com.eevoe.flow.annotation.FlowState;
import com.eevoe.flow.http.response.Res;
import com.mokyun.android.demo0.http.Post;
import com.mokyun.android.demo0.http.TestService;
import com.mokyun.android.demo0.state.ApiClientState;

import junit.framework.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@FlowBindView(view = R.layout.login, hideNav = true, title = "")
public class LoginFragment extends FlowFragment {
    private static String TAG = LoginFragment.class.getSimpleName();

    @FlowState
    ApiClientState mApiClientState;

    @Override
    public void initView(View view) {
        Log.wtf(TAG, "initView: " );

        getNavBarVModel().setVisibility(View.GONE);
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(new LaunchFragment());
            }
        });
        mApiClientState.create(TestService.class).test().enqueue(new Callback<Res<List<Post>>>() {
            @Override
            public void onResponse(Call<Res<List<Post>>> call, Response<Res<List<Post>>> response) {
                Log.wtf("????", response.body().toJsonString());
                response.body();
            }

            @Override
            public void onFailure(Call<Res<List<Post>>> call, Throwable t) {
            }
        });
    }
}
