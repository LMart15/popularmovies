package com.udacity.popularmovies.core;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class ApiAuthenticationInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl authUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", null)
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(authUrl);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
