package com.flightbooking.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    //private static final String ROOT_URL = "http://bookingflight.info/";
    private static final String ROOT_URL = "http://goflightinfo.000webhostapp.com/";
    public static Retrofit getRetrofitInstance() {
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
