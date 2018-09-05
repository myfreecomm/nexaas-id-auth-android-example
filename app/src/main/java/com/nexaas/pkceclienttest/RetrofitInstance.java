package com.nexaas.pkceclienttest;

import android.util.Log;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.nexaas.pkceclienttest.models.ErrorModel;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

public class RetrofitInstance {
    public static final String API_BASE_URL = "https://sandbox.id.nexaas.com/";

    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;

    private static RetrofitInstance instance;
    private static Retrofit retrofit;

    public static Retrofit getInstance () {
        if (instance == null)
            instance = new RetrofitInstance();

        return instance.getRetrofit(null);
    }

    public static Retrofit getInstanceBearer (String bearerToken) {
        if (instance == null)
            instance = new RetrofitInstance();

        return instance.getRetrofit(bearerToken);
    }

    private Retrofit getRetrofit (String bearerToken) {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .client(getClient(bearerToken))
                .build();

        return retrofit;
    }

    private static OkHttpClient getClient (String bearerToken) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

        if (bearerToken == null)
            clientBuilder.addInterceptor(getInterceptor());
        else
            clientBuilder.addInterceptor(getInterceptorBearer(bearerToken));

        return clientBuilder.build();
    }

    private static Interceptor getInterceptor () {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                request.newBuilder()
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader("accept", "application.json;charset=UTF-8")
                        .build();

                return chain.proceed(request);
            }
        };
    }

    private static Interceptor getInterceptorBearer (final String bearer) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                request = request.newBuilder()
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader("accept", "application.json;charset=UTF-8")
                        .addHeader("Authorization", "Bearer " + bearer)
                        .build();

                return chain.proceed(request);
            }
        };
    }

    public static String getError (ResponseBody error) {
        if (error != null) {
            try {
                Converter<ResponseBody, ErrorModel> converter = retrofit.responseBodyConverter(ErrorModel.class, new Annotation[0]);
                ErrorModel errorResponse = converter.convert(error);

                if (errorResponse != null) {
                    if (errorResponse.getErrors() != null)
                        if (errorResponse.getErrors().getMessage() != null) {
                            if (errorResponse.getErrors().getMessage()
                                    .contains("Use JsonReader.setLenient(true)")) {

                                return "Erro desconhecido na conexão";
                            }

                            return errorResponse.getErrors().getMessage();
                        }
                } else
                    throw new IOException();
            } catch (IOException e) {
                try {
                    return error.string();
                } catch (IOException ex) {
                    Log.e("API", e.getMessage());
                    return "Erro desconhecido na conexão";
                }
            }
        }

        return "Erro desconhecido na conexão";
    }

    public static String treatFailureMessage (Throwable t) {
        String message;
        if (t instanceof SocketTimeoutException) {
            message = "O servidor excedeu o tempo limeite de conexão.";
        } else
            message = "O servidor encontrou um erro na requisição";

        return message;
    }
}
