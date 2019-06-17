package com.udacity.popularmovies.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(String baseUrl, Class<S> serviceClass) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.client(getClient()).build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new ApiAuthenticationInterceptor());

        return httpClient.build();
    }
}

