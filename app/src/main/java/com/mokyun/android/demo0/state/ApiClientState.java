package com.mokyun.android.demo0.state;

import android.content.Context;

import com.eevoe.flow.FlowState;
import com.eevoe.flow.FlowStateInterFace;
import com.eevoe.flow.util.ACache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientState implements FlowStateInterFace {

    private static String BASE_URL = "http://internal.mokyun.com:8003/";

    Retrofit mRetrofit;

    @Override
    public void init(FlowState flowState) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(genericClient())
                .build();
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOiIyMDE4LTEwLTIyVDEzOjI4OjI0Ljc4NDgzMSIsImlhdCI6IjIwMTgtMTAtMjJUMTE6Mjg6MjQuNzg0ODE5IiwiaXNzIjoxLCJzdWIiOjAsInR5cCI6Ik1ldGVvciJ9.gUhXuSkjwpHoELTDBl1fWtX3h-aJ4OUULQz6zDiaOiM")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }

    public <T> T create(final Class<T> service) {

        return mRetrofit.create(service);
    }
}
