package com.imuons.pmcindia.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
 * Created by Tabish on 06-02-2020.
 */

public class ApiHandler {

    private static final String DEV_BASE_URL = "https://www.wozur.com/wozur/public/api/";


    private static final long HTTP_TIMEOUT = TimeUnit.SECONDS.toMillis(60);
    private static PMCIndia apiService;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(DEV_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static PMCIndia getApiService() {
        if (apiService == null) {
            httpClient.connectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            httpClient.writeTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            httpClient.readTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            httpClient.retryOnConnectionFailure(true);
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
            Retrofit retrofit = builder.client(httpClient.build()).build();

            apiService = retrofit.create(PMCIndia.class);
            return apiService;
        } else {
            return apiService;
        }
    }


}
