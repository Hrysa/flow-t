package com.mokyun.android.demo0.http;

import com.eevoe.flow.http.response.Res;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface TestService {
  // get test
  @HTTP(method = "GET", path = "test")
  Call<Res<List<Post>>>  test();

  // get ...
  @HTTP(method = "GET", path = "/course/v1/articles")
  Call<Res<List<Post>>> getArticles();

}
